package insurance.shury.insuranceshury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity  implements MyRecyclerViewAdapter.ItemClickListener{
    private static final String TAG = "History";
    MyRecyclerViewAdapter adapter;
    appController apControl=new appController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // data to populate the RecyclerView with

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvUserTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, apControl.getAllUser());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    //open the alert dialog
    @Override
    public void onItemClick(View view, int position) {
        RemarksDialog rD=new RemarksDialog();
        rD.setMessage((adapter.getItem(position).getUserRemarks()));
        rD.show(getSupportFragmentManager(), "Dialog");

    }
}
