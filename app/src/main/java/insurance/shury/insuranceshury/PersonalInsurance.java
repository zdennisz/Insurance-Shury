package insurance.shury.insuranceshury;

public class PersonalInsurance {
    public PersonalInsurance(Insurance insurance, String dateOfPurchase, String remarks) {
        this.insurance = insurance;
        this.dateOfPurchase = dateOfPurchase;
        this.remarks = remarks;
    }

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

    private Insurance insurance;
    private String dateOfPurchase;
    private String remarks;
}
