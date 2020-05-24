package insurance.shury.insuranceshury.model;

public class PersonalInsurance {

    //------------------------------------- Constructor ------------------------------------------

    public PersonalInsurance(Insurance insurance, String dateOfPurchase, String remarks) {
        this.insurance = insurance;
        this.dateOfPurchase = dateOfPurchase;
        this.remarks = remarks;
    }
//------------------------------------- Parameters ------------------------------------------

    private Insurance insurance;
    private String dateOfPurchase;
    private String remarks;

//------------------------------------- Getters & Setters ------------------------------------------

    public Insurance getInsurance() {
        return insurance;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
