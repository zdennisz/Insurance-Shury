package insurance.shury.insuranceshury.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import insurance.shury.insuranceshury.R;
import insurance.shury.insuranceshury.control.appController;

public class History extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    appController apControl=new appController();
    private RecyclerView recyclerView;
    private static final String TAG = "History";
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // data to populate the RecyclerView with
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvUserTable);
        adapter = new MyRecyclerViewAdapter(this, apControl.getAllUser());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setClickListener(this);


    }

    //open the alert dialog
    @Override
    public void onItemClick(View view, int position) {
        RemarksDialog rD = new RemarksDialog();
        rD.setMessage((adapter.getItem(position).getUserRemarks()));
        rD.show(getSupportFragmentManager(), "Dialog");
    }
}
