package com.example.clockapp.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.clockapp.R;
import com.example.clockapp.placeholder.AlarmModel;
import com.example.clockapp.placeholder.alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Add_alarm extends AppCompatActivity {
     private TimePicker timePicker;
     private Map<Integer,String > days = new HashMap<>();
     private LinearLayout dayholder;
     private alarm alarm;
     private int alarm_pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        days.put(2,"T2");
        days.put(3,"T3");
        days.put(4,"T4");
        days.put(5,"T5");
        days.put(6,"T6");
        days.put(7,"T7");
        days.put(8,"CN");

        Intent intent = getIntent();


        alarm_pos = intent.getIntExtra("alarm_pos",-1);


        dayholder = findViewById(R.id.dayHolder);
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);

        for (Map.Entry<Integer,String> entry : days.entrySet()) {

            ToggleButton textView = (ToggleButton) LayoutInflater.from(this).inflate(R.layout.day_layout,dayholder, false);

            //dan don vcl
            textView.setTextOff(entry.getValue());
            textView.setTextOn(entry.getValue());
            textView.setText(entry.getValue());


            textView.setId(entry.getKey());

            dayholder.addView(textView);
        }

    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        getMenuInflater().inflate(R.menu.add_alarm, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //TODO: tao moi bao thuc
        if (item.getItemId() == R.id.AFinish){
            ArrayList<Integer> days = new ArrayList<>();
            ToggleButton tv;

            for (int i =0; i< dayholder.getChildCount() ; i++){
                tv = (ToggleButton) dayholder.getChildAt(i);
                if (tv.isChecked() ){


                    days.add(tv.getId() );
                }
            }
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
            calendar.set(Calendar.MINUTE,timePicker.getMinute());

            alarm =  new alarm("test",days ,calendar.getTimeInMillis());

            Intent intent = getIntent();
            Bundle b = new Bundle();
            b.putSerializable("alarm",alarm);
            b.putInt("alarm_pos",alarm_pos);

            intent.putExtra("alarm",b);

            setResult(2,intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}