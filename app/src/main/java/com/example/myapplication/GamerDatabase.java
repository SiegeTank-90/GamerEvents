package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;

public class GamerDatabase extends SQLiteOpenHelper {
   // private final int Version= 1;
    private static final String Database_Name = "GamerDatabase.db";

    private static GamerDatabase mGamerDb;
    // this needs to be handled better, right now its incrementing independent.
    private static int tempIDinc;
    public enum SubjectSortOrder { ALPHABETIC, UPDATE_GAMES, UPDATE_STORES };




    public GamerDatabase getInstance(Context context) {
        if (mGamerDb == null) mGamerDb = new GamerDatabase(context);
        return mGamerDb;
    }
    private GamerDatabase(Context context) {
        super(context,
                Database_Name,
                null,
                1);
    }

    // Not doing validation for profiles for inital lauch will need later.
    public static final class ProfileTable {
        public static final String TABLE = "PROFILE";
        public static final String COL_USERID = "ID";
        public static final String COL_USERNAME = "UserName";
        public static final String COL_PASSWORD = "Password";
        private static final String COL_IMGref = "Img";
        private static final String COL_GAMES = "Games";
        private static final String COL_GAMESTORES = "Stores";
        private static final String COL_GAMEVENTS = "Events";
        // FUTURE UPDATES FOR ADDING GAMER SCORE/ELO
        private static final String COL_GAMERELO = "GamerELO";

    }
    public static final class StoreTable {
        public static final String TABLE = "STORE";
        public static final String COL_STOREID = "ID";
        public static final String COL_STORENAME = "Name";
        public static final String COL_STOREADDRESS = "Address";
    }

    public static final class GamerEventTable {
        public static final String TABLE = "EVENTS";
        public static final String COL_EVENTID = "ID";
        public static final String COL_EVENTNAME = "Name";
        public static final String COL_DATE = "Date";
        public static final String COL_CurrAttendance = "Attending";
        public static final String COL_MaxAttendance = "MaxAttendance";
        private static final String COL_IMGref = "Img";
        private static final String COL_Location = "Location";
        private static final String COL_GAME = "GAME";
        private static final String COL_EntryFee = "EntryFee";
        // TODO: ADD EVENT DESCRIPTIONS LATER

    }

    // More categories for descriptive purposes will be needed for now names will suffice
    public static final class GamesTable {
        public static final String TABLE = "GAMES";
        public static final String COL_GAMEID = "ID";
        public static final String COL_GAMENAME = "Name";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Profile Table
        //Builds profile with lines to games the player plays and stores.
        //
        //
        //
        //
        db.execSQL("create table " + ProfileTable.TABLE + " (" +
                ProfileTable.COL_USERID + " integer primary key autoincrement, " +
                ProfileTable.COL_USERNAME + ", " +
                ProfileTable.COL_PASSWORD + ", " +
                ProfileTable.COL_IMGref + ", " +
                ProfileTable.COL_GAMES + ", " + "foreign key(" + GamesTable.COL_GAMEID + ") references " + GamesTable.TABLE + "(" + GamesTable.COL_GAMENAME + ") on Delete cascade, " +
                ProfileTable.COL_GAMESTORES + ", " + "foreign key(" + StoreTable.COL_STOREID + ") references " + StoreTable.TABLE + "(" + StoreTable.COL_STORENAME + ") on Delete cascade, " +
                ProfileTable.COL_GAMEVENTS + ", " + "foreign key(" + GamerEventTable.COL_EVENTID + ") references " + GamerEventTable.TABLE + "(" + GamerEventTable.COL_EVENTNAME + ") on Delete cascade, " +
                ProfileTable.COL_GAMERELO + " )"

                );

        // Stores a table of possible stores more useful for later intended features to let players hold multiple stores
        db.execSQL(" create table " + StoreTable.TABLE + " (" +
                StoreTable.COL_STOREID + " integer primary key autoincrement, " +
                StoreTable.COL_STORENAME + ", " +
                StoreTable.COL_STOREADDRESS + ")"
            );

        // Stores a table of games so players could cue of for multiple future games
        db.execSQL("create table " + GamesTable.TABLE + " (" +
                GamesTable.COL_GAMEID + "integer primary key autoincrement, " +
                GamesTable.COL_GAMENAME + " )");

        db.execSQL("create table " + GamerEventTable.TABLE + " (" +
                GamerEventTable.COL_EVENTID + ", " + " integer primary key autoincrement, " +
                GamerEventTable.COL_EVENTNAME + ", " +
                GamerEventTable.COL_DATE + ", " +
                GamerEventTable.COL_CurrAttendance+ "int, " +
                GamerEventTable.COL_MaxAttendance + "int, " +
                GamerEventTable.COL_IMGref + ", " +
                GamerEventTable.COL_Location + ", " +
                GamerEventTable.COL_GAME + ", " + "foreign key(" + GamesTable.COL_GAMEID + ") references " + GamesTable.TABLE + "(" + GamesTable.COL_GAMENAME + ") on Delete cascade, " +
                GamerEventTable.COL_EntryFee + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + GamerEventTable.TABLE);
        db.execSQL("drop table if exists " + GamesTable.TABLE);
        db.execSQL("drop table if exists " + ProfileTable.TABLE);
        db.execSQL("drop table if exists " + StoreTable.TABLE);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                db.execSQL("pragma foreign_keys = on;");
            } else {
                db.setForeignKeyConstraintsEnabled(true);
            }
        }
    }


    // CRUD FOR PROFILE TABLE
    //
    //
    //
    //
    //
    //
    //
    //
    public List<Profile> getProfiles(SubjectSortOrder order) {
        List<Profile> profiles = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // files to sort throught the profiles
        String orderBy = "";
        switch (order) {
            case ALPHABETIC:
                orderBy= ProfileTable.COL_USERNAME + " collate nocase";
                break;
            case UPDATE_STORES:
                orderBy= ProfileTable.COL_GAMESTORES + " collate desc";
                break;
            case UPDATE_GAMES:
                orderBy = ProfileTable.COL_GAMES + " collate asc";
                break;
        }

        String sql = "select * from " + ProfileTable.TABLE + " order by " + orderBy;
        Cursor cursor =  db.rawQuery(sql, null);
        if ( cursor.moveToFirst()) {
            do {
                // create a profile object into the sqllite database
                tempIDinc++;
                String tempIDName = "P" + tempIDinc;
                Profile NewProfile = new Profile(tempIDName);
                // ID is set upon creation and never again
                NewProfile.setProfileName(cursor.getString(1));
                NewProfile.setPassWord(cursor.getString(2));
                NewProfile.setIMGref(cursor.getString(3));
                NewProfile.setFavoriteGame(cursor.getString(4));
                NewProfile.setGameStores(cursor.getString(5));
                NewProfile.setGamerEvents(cursor.getString(6));
                // skip GamerELO its default is always 1000
                profiles.add(NewProfile);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return profiles;
    }

    public boolean addProfile(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProfileTable.COL_USERID, profile.getProfileID());
        values.put(ProfileTable.COL_USERNAME, profile.getProfileName());
        values.put(ProfileTable.COL_PASSWORD, profile.getPassWord());
        values.put(ProfileTable.COL_IMGref, profile.getIMGref());
        values.put(ProfileTable.COL_GAMES, profile.getFavoriteGames());
        values.put(ProfileTable.COL_GAMESTORES, profile.getGameStores());
        values.put(ProfileTable.COL_GAMEVENTS,profile.getGamerEvents());
        values.put(ProfileTable.COL_GAMERELO,profile.getGamerELO());
        long id = db.insert(ProfileTable.TABLE, null, values);

        return id != -1;
    }

    public boolean updateProfile(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProfileTable.COL_USERID, profile.getProfileID());
        values.put(ProfileTable.COL_USERNAME, profile.getProfileName());
        values.put(ProfileTable.COL_PASSWORD, profile.getPassWord());
        values.put(ProfileTable.COL_IMGref, profile.getIMGref());
        values.put(ProfileTable.COL_GAMES, profile.getFavoriteGames());
        values.put(ProfileTable.COL_GAMESTORES, profile.getGameStores());
        values.put(ProfileTable.COL_GAMEVENTS,profile.getGamerEvents());
        // TODO: ADD UPDATE TO COMPLETE UPDATE

        //return id != -1;
        return false;
    }

    public void deleteProfile(Profile profile){
        SQLiteDatabase db = getWritableDatabase();
        //TODO:Add DELETE TO PROJECT
    }


    //CRUD FOR GAMES STORES TABLES
    //
    //
    //
    //
    //
    //
    //
    //
    //
    public List<GameStore> getStores(GameStore store) {
        List<GameStore> stores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + StoreTable.TABLE +
                " where " + StoreTable.COL_STORENAME + " = ?";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                // create a GameStore object into the sqllite database
                tempIDinc++;
                String tempStoreIDName = "GS" + tempIDinc;
                GameStore newStore = new GameStore(tempStoreIDName);
                newStore.setStoreName(cursor.getString(1));
                newStore.setStoreAddress(cursor.getString(2));
                stores.add(newStore);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return stores;
    }

    public boolean addGamesStore(GameStore gameStore) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StoreTable.COL_STOREID, gameStore.getStoreID());
        values.put(StoreTable.COL_STORENAME, gameStore.getStoreName());
        values.put(StoreTable.COL_STOREADDRESS, gameStore.getStoreAddress());
        long id = db.insert(StoreTable.TABLE, null, values);

        return id != -1;
    }

    public boolean updateStores(GameStore store) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StoreTable.COL_STOREID, store.getStoreID());
        values.put(StoreTable.COL_STORENAME, store.getStoreAddress());
        values.put(StoreTable.COL_STOREADDRESS, store.getStoreName());

        // TODO: ADD UPDATE TO COMPLETE UPDATE

        //return id != -1;
        return false;
    }
    //
    public void deleteStores(GameStore store){
        SQLiteDatabase db = getWritableDatabase();
        //TODO:Add DELETE TO STORE TABLE
    }


    /// CRUD for GAMES TABLE
    //
    //
    //
    //
    //
    //
    public List<Game> getGames(Game game) {
        List<Game> games = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + GamesTable.TABLE +
                " where " + GamesTable.COL_GAMENAME + " = ?";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                // create a GameStore object into the sqllite database
                tempIDinc++;
                String tempGameIDName = "GA" + tempIDinc;
                Game newGame = new Game(tempGameIDName);
                newGame.setGameName(cursor.getString(1));
                games.add(game);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return games;
    }

    public boolean addGames(Game game) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GamesTable.COL_GAMEID, game.getGameID());
        values.put(GamesTable.COL_GAMENAME, game.getGameName());
        long id = db.insert(GamesTable.TABLE, null, values);
        return id != -1;
    }


    public boolean updateGames(Game game) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StoreTable.COL_STOREID, game.getGameID());
        values.put(StoreTable.COL_STORENAME, game.getGameName());

        // TODO: ADD UPDATE TO COMPLETE UPDATE

        //return id != -1;
        return false;
    }
    //
    public void deleteGames(Game game){
        SQLiteDatabase db = getWritableDatabase();
        //TODO:Add DELETE TO STORE TABLE
    }


    // CRUD FOR EVENTS TABLE GAMES
    //
    //
    //
    //
    //
    //
    //
    public List<GamerEvent> getGamerEvents(GamerEvent event) {
        List<GamerEvent> events = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + GamerEventTable.TABLE +
                " where " + GamerEventTable.COL_EVENTID + " = ?";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                // create a GameStore object into the sqlite database
                tempIDinc++;
                String tempEventsIDName = "GE" + tempIDinc;
                GamerEvent newGameEvent = new GamerEvent(tempEventsIDName);
                newGameEvent.setEventName(cursor.getString(1));
                newGameEvent.setDate(cursor.getString(2));
                newGameEvent.setCurrAttendance(cursor.getInt(3));
                newGameEvent.setMaxAttendance(cursor.getInt(4));
                newGameEvent.setIMGref(cursor.getString(5));
                newGameEvent.setLocation(cursor.getString(6));
                newGameEvent.setGame(cursor.getString(7));
                newGameEvent.setEntryFee(cursor.getString(8));
                events.add(newGameEvent);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return events;
    }

    public boolean addEvents(GamerEvent event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GamerEventTable.COL_EVENTID, event.getEventID());
        values.put(GamerEventTable.COL_EVENTNAME, event.getEventName());
        values.put(GamerEventTable.COL_DATE, event.getDate());
        values.put(GamerEventTable.COL_CurrAttendance, event.getCurrAttendance());
        values.put(GamerEventTable.COL_MaxAttendance, event.getMaxAttendance());
        values.put(GamerEventTable.COL_GAME,event.getGame());
        values.put(GamerEventTable.COL_EntryFee,event.getEntryFee());
        long id = db.insert(GamerEventTable.TABLE, null, values);

        return id != -1;
    }

    public boolean updateEvents(GamerEvent event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GamerEventTable.COL_EVENTID, event.getEventID());
        values.put(GamerEventTable.COL_EVENTNAME, event.getEventName());
        values.put(GamerEventTable.COL_DATE, event.getDate());
        values.put(GamerEventTable.COL_CurrAttendance, event.getCurrAttendance());
        values.put(GamerEventTable.COL_MaxAttendance, event.getMaxAttendance());
        values.put(GamerEventTable.COL_IMGref, event.getIMGref());
        values.put(GamerEventTable.COL_Location,event.getLocation());
        values.put(GamerEventTable.COL_EntryFee,event.getEntryFee());

        // TODO: ADD UPDATE TO COMPLETE UPDATE
        //return id != -1;
        return false;
    }

    public void deleteEvent(GamerEvent event){
        SQLiteDatabase db = getWritableDatabase();
        //TODO:Add DELETE TO PROJECT
    }


}
