package insurance.shury.insuranceshury.model;

import java.util.HashMap;

public class DB {


    //------------------------------------- Singleton ------------------------------------------
    private static DB instance = null;

    private DB() {
    }

    public static DB getDB() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    //------------------------------------- Parameters ------------------------------------------

    private HashMap<Integer, User> userHashMap = new HashMap<Integer, User>();
    private HashMap<Integer, String> designedCreatedHashMap = new HashMap<Integer, String>();
    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }
    public HashMap<Integer, String> getDesignedCreatedHashMap() {
        return designedCreatedHashMap;
    }

    //------------------------------------- Getters & Setters ------------------------------------------

    public void setUserHashMap(HashMap<Integer, User> userHashMap) {
        this.userHashMap = userHashMap;
    }

    public void setDesignedCreatedHashMap(HashMap<Integer, String> designedCreatedHashMap) {
        this.designedCreatedHashMap = designedCreatedHashMap;
    }

}
