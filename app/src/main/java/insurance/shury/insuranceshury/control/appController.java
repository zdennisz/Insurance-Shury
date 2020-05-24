package insurance.shury.insuranceshury.control;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import insurance.shury.insuranceshury.model.DB;
import insurance.shury.insuranceshury.model.Insurance;
import insurance.shury.insuranceshury.model.InsuranceType;
import insurance.shury.insuranceshury.model.PersonalInsurance;
import insurance.shury.insuranceshury.model.User;

public class appController {
    private static final String FILE_NAME = "users.txt";
    private DB dbInstance;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }


    public appController() {
        dbInstance = DB.getInstance();
    }


    public HashMap getAllUser() {
        return this.dbInstance.getUserHashMap();
    }

    public HashMap getDesignerCreator() {
        return this.dbInstance.getDesignedCreatedHashMap();
    }

    public User getUser(int id) {
        return this.dbInstance.getUserHashMap().get(id);
    }


    public void addUser(String firstName, String lastName, String date, InsuranceType type, String remarks) {
        boolean foundUser = false;
        //if the user exists we add him to the hashmap
        HashMap<Integer, User> dbRef = dbInstance.getUserHashMap();
        for (Map.Entry<Integer, User> entry : dbRef.entrySet()) {
            if (entry.getValue().getLastName().equals(lastName) && entry.getValue().getFirstName().equals(firstName)) {
                entry.getValue().personalInsurance.add(new PersonalInsurance(new Insurance(type), date, remarks));
            }
        }

        //if the user dosent exist add new entry
        User newUser = new User(firstName, lastName);
        int location = dbRef.size();
        newUser.setID(location);
        newUser.personalInsurance.add(new PersonalInsurance(new Insurance(type), date, remarks));
        dbRef.put(location, newUser);
    }

    public void saveToFile(String firstName, String lastName, String date, InsuranceType type, String remarks) {
        String comma = ",";
        String strType = type.name();
        String endOfLine = "\n";
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, context.MODE_APPEND);
            fos.write(firstName.getBytes());
            fos.write(comma.getBytes());
            fos.write(lastName.getBytes());
            fos.write(comma.getBytes());
            fos.write(date.getBytes());
            fos.write(comma.getBytes());
            fos.write(strType.getBytes());
            fos.write(comma.getBytes());
            fos.write(remarks.getBytes());
            fos.write(endOfLine.getBytes());
            Toast.makeText(context, "Saved to " + context.getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "There was a problem saving the the user" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void importUsers() {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                List<String> userlist = Arrays.asList(text.split(","));
                InsuranceType type = InsuranceType.valueOf(InsuranceType.class, userlist.get(3));
                addUser(userlist.get(0), userlist.get(1), userlist.get(2), type, userlist.get(4));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importDesingerCreator() {
        //access sharedprefrences
        //save data in this db
        // dbInstance.setDesignedCreatedHashMap();
        //designedCreatedHashMap -load from file
    }

}
