package insurance.shury.insuranceshury;

import java.util.ArrayList;
import java.util.Date;

public class User {

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private int ID;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;


    public ArrayList<PersonalInsurance> personalInsurance = new ArrayList<PersonalInsurance>();



}
