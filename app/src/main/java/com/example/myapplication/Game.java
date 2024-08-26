package com.example.myapplication;

public class Game {
    private final String GameID;
    private String GameName;

    public Game(String gameID) {
        GameID = gameID;
        GameName = "";
    }

    public String getGameID() {
        return GameID;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }
}
