package de.judev.mcdashboard.player;

public record Player(String uuid, String name) {
    private static final String baseUrl = "https://crafatar.com/";

    public String getHead() {
        return baseUrl + "avatars/" + uuid;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSkin() {
        return baseUrl + "skins/" + uuid;
    }

    public String getBody() {
        return baseUrl + "renders/body/" + uuid;
    }
}