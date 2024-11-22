package com.example.clockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.clockapp.R;
import com.example.clockapp.placeholder.AlarmModel;
import com.example.clockapp.placeholder.alarm;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Add_alarm extends AppCompatActivity {
     private TimePicker timePicker;
     private Map<Integer,String > days = new HashMap<>();
     private LinearLayout dayholder;
     private AlarmModel vm;
     private alarm alarm;
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

        days.put(0,"T2");
        days.put(1,"T3");
        days.put(2,"T4");
        days.put(3,"T5");
        days.put(4,"T6");
        days.put(5,"T7");
        days.put(6,"CN");

        vm = new ViewModelProvider(this).get(AlarmModel.class);

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
            Map<Integer,String> days = new HashMap<>();
            ToggleButton tv;
            for (int i =0; i< dayholder.getChildCount() ; i++){
                tv = (ToggleButton) dayholder.getChildAt(i);
                if (tv.isChecked() ){

                    days.put(tv.getId(), tv.getTextOn().toString() );
                }
            }
            Calendar calendar = new GregorianCalendar();

            calendar.add(Calendar.HOUR,timePicker.getCurrentHour());
            calendar.add(Calendar.MINUTE,timePicker.getCurrentMinute());
            alarm =  new alarm("test",days ,calendar);
            Intent intent = getIntent();
            Bundle b = new Bundle();
            b.putSerializable("alarm",alarm);
            intent.putExtra("alarm",b);
            setResult(2,intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}