package insurance.shury.insuranceshury.control;

import android.content.Context;
import android.util.JsonReader;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONException;
import org.json.JSONObject;

import insurance.shury.insuranceshury.model.DB;
import insurance.shury.insuranceshury.model.Insurance;
import insurance.shury.insuranceshury.model.InsuranceType;
import insurance.shury.insuranceshury.model.PersonalInsurance;
import insurance.shury.insuranceshury.model.User;


public class appController {

    //------------------------------------- Constructor ------------------------------------------

    public appController() {
        dbInstance = DB.getInstance();
    }

    //-------------------------------- Parameters -------------------------------------


    private static final String FILE_DEVELOPERS = "input.json";
    private static final String FILE_NAME = "users.txt";
    private DB dbInstance;
    private Context context;


    //-------------------------------- Getters & Setters -------------------------------------

    public void setContext(Context context) {
        this.context = context;
    }

    public HashMap<Integer, User> getAllUser() {
         return this.dbInstance.getUserHashMap();

    }

    public HashMap getDesignerCreator() {
        return this.dbInstance.getDesignedCreatedHashMap();
    }


    //-------------------------------- Methods -------------------------------------

    public void addUserToDB(String firstName, String lastName, String date, InsuranceType type, String remarks) {
        //if the user exists we add him to the hashmap
        boolean userFound=false;
        HashMap<Integer, User> dbRef = dbInstance.getUserHashMap();
        for (Map.Entry<Integer, User> entry : dbRef.entrySet()) {
            if (entry.getValue().getLastName().equals(lastName) && entry.getValue().getFirstName().equals(firstName)) {
                entry.getValue().personalInsurance.add(new PersonalInsurance(new Insurance(type), date, remarks));
                userFound=true;
            }
        }
        if(!userFound){
            //if the user dosent exist add new entry
            User newUser = new User(firstName, lastName);
            int location = dbRef.size();
            newUser.setID(location);
            newUser.personalInsurance.add(new PersonalInsurance(new Insurance(type), date, remarks));
            dbRef.put(location, newUser);
        }

    }


    //-------------------------------- Users TXT Writer -------------------------------------

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
            Toast.makeText(context, "Saved to data/data/insurance.shury.insuranceshury/files/"  + FILE_NAME, Toast.LENGTH_LONG).show();
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

    //-------------------------------- Users TXT Reader -------------------------------------

    public void importUsers() {
        File file = new File(context.getFilesDir(),FILE_NAME);
        if(file.exists()) {
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
                    addUserToDB(userlist.get(0), userlist.get(1), userlist.get(2), type, userlist.get(4));
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
        }else{
        //prevent empty access to file
        }
    }

    public void importDesingerCreator() {
       String json;
        HashMap<Integer,String> desingerCreator=new HashMap<Integer, String>();
        try{
           //read from the file
           InputStream is=context.getAssets().open(FILE_DEVELOPERS);
           //gets the size of the file
           int size=is.available();
           //allocates array according to file size
           byte[] buffer=new byte[size];
           //read the file into buffer
           is.read(buffer);
           //closes the data stream
            is.close();
           //convert buffer to string
            json=new String(buffer,"UTF-8");
           //convert the string to array of json objects
           JSONObject obj=new JSONObject(json);
           desingerCreator.put(0,"Version "+(obj.getString("version")));
           desingerCreator.put(1,"Designed & Developed by:");
           desingerCreator.put(2,obj.getString("student_name_1"));
           desingerCreator.put(3,obj.getString("student_name_2"));

       }catch (IOException ex){
           ex.printStackTrace();
       } catch (JSONException e) {
            e.printStackTrace();
        }
         dbInstance.setDesignedCreatedHashMap(desingerCreator);

    }
}
