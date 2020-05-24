package insurance.shury.insuranceshury;

import android.content.SharedPreferences;

import java.util.HashMap;

public class DB {
    private static DB instance;

    public void setUserHashMap(HashMap<Integer, User> userHashMap) {
        this.userHashMap = userHashMap;
    }

    public void setDesignedCreatedHashMap(HashMap<Integer, String> designedCreatedHashMap) {
        this.designedCreatedHashMap = designedCreatedHashMap;
    }

    private HashMap<Integer,User> userHashMap=new HashMap<Integer,User>();
    private HashMap<Integer,String> designedCreatedHashMap=new HashMap<Integer,String>();
    private DB(){

    }

    public static DB getInstance() {
        if(instance==null){
            instance=new DB();
        }
        return instance;
    }


    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public HashMap<Integer, String> getDesignedCreatedHashMap() {
        return designedCreatedHashMap;
    }





}
