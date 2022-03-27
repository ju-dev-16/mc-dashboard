package de.judev.mcdashboard.player;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PlayerList {
    PlayerController playerController = new PlayerController();
    public List<Player> playerList = new ArrayList<>();
    List<String> data = new ArrayList<>();

    public void readFile(String filename) {
        try {
            String response = new String((Files.readAllBytes(Paths.get(filename))));
            JSONArray jsonarray = new JSONArray(response);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String uuid = jsonobject.getString("uuid");
                data.add(uuid);
            }

            setList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setList() {
        for (int i = 0; i < data.size(); i++) {
            Player player = new Player(data.get(i), playerController.fetch("https://api.mojang.com/user/profiles/" + data.get(i) + "/names"));
            playerList.add(player);
        }
    }

    public List<Player> getList() {
        return playerList;
    }
}