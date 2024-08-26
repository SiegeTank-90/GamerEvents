package com.example.myapplication;

public class GamerEvent {
    private final String EventID;
    private String EventName;
    //Date will need to be a date object in the future to interact with phone data aspects.
    private String Date;
    private int CurrAttendance;
    private int maxAttendance;
    private String IMGref;
    private String Location;
    private String Game;
    private String EntryFee;

    public GamerEvent(String eventID) {
        EventID = eventID;
        EventName = "";
        Date = "";
        CurrAttendance = 0;
        this.maxAttendance = 1;
        this.IMGref = "";
        Location = "";
        Game = "";
        EntryFee = "";
    }

    public String getEventID() {
        return EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCurrAttendance() {
        return CurrAttendance;
    }

    public void setCurrAttendance(int currAttendance) {
        CurrAttendance = currAttendance;
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public void setMaxAttendance(int maxAttendance) {
        this.maxAttendance = maxAttendance;
    }

    public String getIMGref() {
        return IMGref;
    }

    public void setIMGref(String IMGref) {
        this.IMGref = IMGref;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getGame() {
        return Game;
    }

    public void setGame(String game) {
        Game = game;
    }

    public String getEntryFee() {
        return EntryFee;
    }

    public void setEntryFee(String entryFee) {
        EntryFee = entryFee;
    }
}
