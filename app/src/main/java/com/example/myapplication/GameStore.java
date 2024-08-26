package com.example.myapplication;

public class GameStore {

        private final String StoreID;
        private String StoreAddress;
        private String StoreName;

    public GameStore(String storeID) {
        StoreID = storeID;
        StoreAddress = "";
        StoreName = "";
    }

    public String getStoreID() {
        return StoreID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

}
