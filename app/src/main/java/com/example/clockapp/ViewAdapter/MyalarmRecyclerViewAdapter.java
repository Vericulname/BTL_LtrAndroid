package com.example.clockapp.ViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.clockapp.alarmReceiver;
import com.example.clockapp.databinding.FragmentAlarmItemBinding;
import com.example.clockapp.interfaces.RecycleItemClick;
import com.example.clockapp.placeholder.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link alarm}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyalarmRecyclerViewAdapter extends RecyclerView.Adapter<MyalarmRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<alarm> mValues;
    private static RecycleItemClick recycleItemClick;
    private AlarmManager alarmManager;
    private Context context;
    private Intent intent;
    private PendingIntent pendingIntent;


    public MyalarmRecyclerViewAdapter(ArrayList<alarm>  items,AlarmManager alarmManager,Context context,Intent intent,RecycleItemClick recycleItemClick ) {
        mValues = items;
        this.context = context;
        this.intent = intent;
        MyalarmRecyclerViewAdapter.recycleItemClick = recycleItemClick;
        this.alarmManager = alarmManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAlarmItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //khai bao bien
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mValues.get(position).getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("vi","VN"));

        String sTime = String.format(new Locale("vi","VN"),
                "%02d:%02d",
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));

        StringBuilder sDay = new StringBuilder();

        for (int i : mValues.get(position).getDays()){
            calendar.set(Calendar.DAY_OF_WEEK,i);
            sDay.append(sdf.format( calendar.getTime() )).append(",");

        }

        holder.mtxTime.setText(sTime);
        holder.mtxDayOfweek.setText(sDay.toString());
        holder.mSwitch.setChecked(mValues.get(position).isStatus());


        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            mValues.get(holder.getAdapterPosition()).getTime(),
                            pendingIntent);
                    Log.v("alarm "+holder.getAdapterPosition(),"on");
                    mValues.get(holder.getAdapterPosition()).setStatus(true);
                }
                else {

                    alarmManager.cancel(pendingIntent);
                    Log.v("alarm "+ holder.getAdapterPosition(),"off");
                    mValues.get(holder.getAdapterPosition()).setStatus(false);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mtxTime;
        public final TextView mtxDayOfweek;
        public final Switch mSwitch;
        public alarm mItem;

        public ViewHolder(FragmentAlarmItemBinding binding) {
            super(binding.getRoot());
            mtxTime = binding.txTime;
            mtxDayOfweek = binding.txDayOfWeek;
            mSwitch = binding.switch1;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (recycleItemClick !=null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recycleItemClick.onRecycleItemClick(pos);
                        }
                    }
                }
            });
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}