package insurance.shury.insuranceshury.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import insurance.shury.insuranceshury.R;
import insurance.shury.insuranceshury.control.appController;

public class Home extends AppCompatActivity {

    private Button viewBtn;
    private Button buyBtn;
    private TextView designerCreatorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //import users
        appController.getAppController().setContext(this.getApplicationContext());
        appController.getAppController().importUsers();
        //import designer and creator tag
        //apControl.importDesingerCreator();
        buyBtn = findViewById(R.id.buy_Btn);
        viewBtn = findViewById(R.id.view_Btn);
        designerCreatorTv = findViewById(R.id.designedCreated_tv);
        //parseDesignerCreator();
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, BuyInsurance.class));
                //finish();
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, History.class));
               // finish();

            }
        });
    }


    public void parseDesignerCreator() {
        String names = "";
        HashMap<Integer, String> designerCreator;
        designerCreator =  appController.getAppController().getDesignerCreator();
        for (Map.Entry<Integer, String> entry : designerCreator.entrySet()) {
            String value = entry.getValue();
            names += value + " \n";
        }
        designerCreatorTv.setText(names);
    }

}
