package insurance.shury.insuranceshury.model;

public class Insurance {
    public int getID() {
        return ID;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Insurance(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    private int ID;
    private InsuranceType insuranceType;

}
