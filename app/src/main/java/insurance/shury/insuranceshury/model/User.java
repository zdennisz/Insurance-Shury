package insurance.shury.insuranceshury.model;

import java.util.ArrayList;

public class User {

    //------------------------------------- Constructor ------------------------------------------

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //------------------------------------- Parameters ------------------------------------------

    private int ID;

    private String firstName;
    private String lastName;

    public ArrayList<PersonalInsurance> personalInsurance = new ArrayList<PersonalInsurance>();

    //------------------------------------- Getters & Setters ------------------------------------------

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


    public ArrayList<PersonalInsurance> getPersonalInsurance() {
        return personalInsurance;
    }

    public void setPersonalInsurance(ArrayList<PersonalInsurance> personalInsurance) {
        this.personalInsurance = personalInsurance;
    }

}
