package insurance.shury.insuranceshury.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import insurance.shury.insuranceshury.R;
import insurance.shury.insuranceshury.model.RecyclerViewUser;
import insurance.shury.insuranceshury.model.User;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private ArrayList<RecyclerViewUser> mData = new ArrayList<RecyclerViewUser>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    MyRecyclerViewAdapter(Context context, HashMap<Integer, User> data) {
        this.mInflater = LayoutInflater.from(context);
        ArrayList<RecyclerViewUser> changedValues=new ArrayList<RecyclerViewUser>();
        ArrayList<User> temp=new ArrayList<User>(data.values());
        for(int i=0;i<data.size();i++){
            RecyclerViewUser user = new RecyclerViewUser();
            user.setUserFirstName(data.get(i).getFirstName());
            user.setUserLastName(data.get(i).getLastName());
            for(int j=0;j<data.get(i).personalInsurance.size();j++){
                user.setDateOfPurchase(data.get(i).personalInsurance.get(j).getDateOfPurchase());
                user.setUserRemarks(data.get(i).personalInsurance.get(j).getRemarks());
                user.setTypeOfInsurance(data.get(i).personalInsurance.get(j).getInsurance().getInsuranceType().name());
            }
            changedValues.add(user);
        }
        mData=changedValues;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RecyclerViewUser person = mData.get(position);
        holder.name.setText(person.getUserFirstName());
        holder.familyname.setText(person.getUserLastName());
        holder.date.setText(person.getDateOfPurchase());
        String remarks = "Remarks";
        holder.remarks.setText(remarks);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView familyname;
        TextView date;
        Button remarks;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_firstName);
            familyname = itemView.findViewById(R.id.rv_lastName);
            date = itemView.findViewById(R.id.rv_date);
            remarks = itemView.findViewById(R.id.rv_remarks);
            remarks.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    RecyclerViewUser getItem(int id) {
        return mData.get(id);

    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
