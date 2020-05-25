package insurance.shury.insuranceshury.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

import insurance.shury.insuranceshury.R;
import insurance.shury.insuranceshury.control.appController;
import insurance.shury.insuranceshury.model.InsuranceType;

public class BuyInsurance extends AppCompatActivity {
    DatePickerDialog picker;
    private EditText et_date;
    private EditText et_remarks;
    private EditText et_firstName;
    private EditText et_lastName;
    private Switch carInsurance;
    private Switch lifeInsurance;
    private Switch disabilityInsurance;
    private Switch apartamentInsurance;
    private Button addInsuranceButton;
    private InsuranceType type;
    private String firstName;
    private String lastName;
    private String date;
    private String remarks;
    private Boolean checkedOne = false;
    private Context context;
    appController apControl=new appController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_insurance);
        apControl.setContext(getApplicationContext());

        initBuyInsuranceView();

        et_date.setInputType(InputType.TYPE_NULL);
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                picker = new DatePickerDialog(BuyInsurance.this, R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }

        });
        carInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifeInsurance.setChecked(false);
                disabilityInsurance.setChecked(false);
                apartamentInsurance.setChecked(false);
                type = InsuranceType.CAR;
            }
        });

        lifeInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carInsurance.setChecked(false);
                disabilityInsurance.setChecked(false);
                apartamentInsurance.setChecked(false);
                type = InsuranceType.LIFE;
            }
        });

        disabilityInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carInsurance.setChecked(false);
                lifeInsurance.setChecked(false);
                apartamentInsurance.setChecked(false);
                type = InsuranceType.WORKSAFTEY;
            }
        });
        apartamentInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carInsurance.setChecked(false);
                lifeInsurance.setChecked(false);
                disabilityInsurance.setChecked(false);
                type = InsuranceType.APARTAMENT;
            }
        });


        addInsuranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = et_firstName.getText().toString();
                lastName = et_lastName.getText().toString();
                date = et_date.getText().toString();
                remarks = et_remarks.getText().toString();

                boolean isValid = isBuyInsuranceValid();

                if (isValid) {

                    //go back to the beggining and send the data
                    apControl.addUser(firstName, lastName, date, type, remarks);
                    apControl.saveToFile(firstName, lastName, date, type, remarks);

                    startActivity(new Intent(BuyInsurance.this, Home.class));
                    //finish();

                }

            }
        });
    }


    public void initBuyInsuranceView() {

        et_date = findViewById(R.id.userDateOfPurchase_et);
        et_remarks = findViewById(R.id.userRemarks_et);
        et_firstName = findViewById(R.id.userFirstName_et);
        et_lastName = findViewById(R.id.userLastName_et);
        carInsurance = findViewById(R.id.switch_carInsurance);
        lifeInsurance = findViewById(R.id.switch_LifeInsurance);
        disabilityInsurance = findViewById(R.id.switch_disabilityInsurance);
        apartamentInsurance = findViewById(R.id.switch_apartamentInssurance);
        addInsuranceButton = findViewById(R.id.addInsurance_btn);

    }


    public boolean isBuyInsuranceValid() {

        //check if someone is not checked
        if (carInsurance.isChecked() || lifeInsurance.isChecked() || disabilityInsurance.isChecked() || apartamentInsurance.isChecked()) {
            checkedOne = true;
        } else {
            checkedOne = false;
        }

        //check if all are filled
        if ((date.isEmpty() || (firstName.isEmpty())) || (lastName.isEmpty()) || type == null || (remarks.isEmpty()) || (!checkedOne)) {
            Toast.makeText(BuyInsurance.this, "Please fill all the fields!", Toast.LENGTH_LONG).show();
            return false;

        } else {
            Toast.makeText(BuyInsurance.this, "Fields are filled", Toast.LENGTH_LONG).show();
            return true;
        }
    }

}
