package de.judev.mcdashboard.player;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PlayerController {

    private HttpRequest request(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
    }

    public String fetch(String url) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request(url), HttpResponse.BodyHandlers.ofString());
            JSONArray json = new JSONArray(response.body());

            return json.getJSONObject(json.length() - 1).getString("name");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}