package com.example.clockapp.ViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.clockapp.alarmReceiver;
import com.example.clockapp.databinding.FragmentAlarmItemBinding;
import com.example.clockapp.placeholder.alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link alarm}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyalarmRecyclerViewAdapter extends RecyclerView.Adapter<MyalarmRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<alarm> mValues;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public MyalarmRecyclerViewAdapter(ArrayList<alarm>  items,PendingIntent pendingIntent,AlarmManager alarmManager) {
        mValues = items;
        this.pendingIntent = pendingIntent;
        this.alarmManager = alarmManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAlarmItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String sTime = mValues.get(position).getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + mValues.get(position).getCalendar().get(Calendar.MINUTE);

        StringBuilder sDay = new StringBuilder();
        for (Map.Entry<Integer,String> entry : mValues.get(position).getDays().entrySet()){

            sDay.append(entry.getValue()).append(", ");
        }

        holder.mtxTime.setText(sTime);
        holder.mtxDayOfweek.setText(sDay);

        holder.mSwitch.setChecked(mValues.get(position).isStatus());


        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Log.v("alarm "+holder.getAdapterPosition(),"on");

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            mValues.get(holder.getAdapterPosition()).getCalendar().getTimeInMillis(),
                            pendingIntent);
                }
                else {

                    alarmManager.cancel(pendingIntent);
                    Log.v("alarm "+ holder.getAdapterPosition(),"off");
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

        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}