module de.judev.mcdashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens de.judev.mcdashboard to javafx.fxml;
    exports de.judev.mcdashboard;
}