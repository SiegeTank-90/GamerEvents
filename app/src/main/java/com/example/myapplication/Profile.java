package com.example.myapplication;

import java.util.ArrayList;

public class Profile {

    private static String ProfileID;
    private String ProfileName;
    private String PassWord;
    private String IMGref;
    private String FavoriteGames;
    private String GameStores;
    private String GamerEvents;
    private int GamerELO;



    public Profile(String profileID) {
        ProfileID = profileID;
        ProfileName = "";
        this.PassWord = "";
        this.IMGref = "";
        FavoriteGames = "";
        GameStores = "";
        GamerEvents = "";
        GamerELO = 1000;
    }

    public static String getProfileID() {
        return ProfileID;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getIMGref() {
        return IMGref;
    }

    public void setIMGref(String IMGref) {
        this.IMGref = IMGref;
    }

    public String getFavoriteGames() {
        return FavoriteGames;
    }

    public void setFavoriteGame(String Games) {
        FavoriteGames = Games;
    }

    public String getGameStores() {
        return GameStores;
    }

    public void setGameStores(String gameStores) {
        GameStores = gameStores;
    }

    public String getGamerEvents() {
        return GamerEvents;
    }

    public void setGamerEvents(String gamerEvents) {
        GamerEvents = gamerEvents;
    }

    public int getGamerELO() {
        return GamerELO;
    }

    public void setGamerELO(int gamerELO) {
        GamerELO = gamerELO;
    }





}
