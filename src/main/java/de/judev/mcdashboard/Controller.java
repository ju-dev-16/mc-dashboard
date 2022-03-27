package de.judev.mcdashboard;

import de.judev.mcdashboard.player.PlayerList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    PlayerList playerList = new PlayerList();

    @FXML
    private AnchorPane node;

    @FXML
    private TextField directory;

    @FXML
    private ListView<Label> list;

    @FXML
    private ImageView currentImg;

    @FXML
    private Label currentLabel;

    @FXML
    private TextField searchbar;

    @FXML
    private ImageView body;

    @FXML
    private TextField name;

    @FXML
    private TextField uuid;

    @FXML
    void directory(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            list.getItems().clear();
            list.getItems().removeAll();
            playerList.getList().clear();
            node.setCursor(Cursor.WAIT);
            playerList.readFile(directory.getText());
            setLabel();
        }
    }

    @FXML
    void fileChooser() {
        list.getItems().clear();
        list.getItems().removeAll();
        playerList.getList().clear();
        node.setCursor(Cursor.WAIT);
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        directory.setText(file.getAbsolutePath());
        playerList.readFile(file.getAbsolutePath());
        setLabel();
    }

    private void setLabel() {
        list.getItems().removeAll();
        searchbar.clear();
        node.setCursor(Cursor.DEFAULT);
        for (int i = 0; i < playerList.getList().size(); i++) {
            try {
                Label lbl = new Label(playerList.getList().get(i).getName());
                ImageView imageView = new ImageView(new Image(playerList.getList().get(i).getHead()));
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                lbl.setGraphic(imageView);
                setStats(i, lbl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        searchbar.setDisable(false);
        currentImg.setImage(new Image(String.valueOf(getClass().getResource("user.png"))));
        currentLabel.setText("Click on a player");
        currentLabel.setLayoutX(440);
    }

    @FXML
    void search() {
        currentLabel.setText("Click on a player");
        currentImg.setImage(new Image(String.valueOf(getClass().getResource("user.png"))));
        currentLabel.setLayoutX(440);
        body.setImage(null);
        name.setOpacity(0);
        uuid.setOpacity(0);
        list.getItems().clear();
        for (int i = 0; i < playerList.getList().size(); i++) {
            if (playerList.getList().get(i).getName().contains(searchbar.getText())) {
                Label currentPlayer = new Label(playerList.getList().get(i).getName());
                ImageView imageView = new ImageView(new Image(playerList.getList().get(i).getHead()));
                currentPlayer.setGraphic(imageView);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                setStats(i, currentPlayer);
                currentLabel.setText("Click on a player");
            } else if (searchbar.getText().equals("")) {
                setLabel();
            }
        }
    }

    private void setStats(int i, Label currentPlayer) {
        list.getItems().add(currentPlayer);
        currentPlayer.setOnMouseClicked(event -> {
            currentImg.setImage(null);
            currentLabel.setText(null);
            body.setImage(new Image(playerList.getList().get(i).getBody()));
            name.setOpacity(1);
            name.setText(playerList.getList().get(i).getName());
            uuid.setOpacity(1);
            uuid.setText(playerList.getList().get(i).getUUID());
        });
    }

    @FXML
    void download() {

    }

    @FXML
    void initialize() {
        assert directory != null : "fx:id=\"directory\" was not injected: check your FXML file 'view.fxml'.";
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'view.fxml'.";
        directory.setFocusTraversable(false);
    }
}