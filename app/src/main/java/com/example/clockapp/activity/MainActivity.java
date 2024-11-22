package com.example.clockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.clockapp.R;
import com.example.clockapp.databinding.FragmentClockItemsBinding;
import com.example.clockapp.fragments.fragmentManager;
import com.example.clockapp.placeholder.AlarmModel;
import com.example.clockapp.placeholder.Clockmodel;
import com.example.clockapp.placeholder.alarm;
import com.example.clockapp.placeholder.clock;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Clockmodel clockmodel;
    private AlarmModel alarmModel;
    private int MenuId = 0;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        clockmodel = new ViewModelProvider(this).get(Clockmodel.class);
        alarmModel = new ViewModelProvider(this).get(AlarmModel.class);


        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new fragmentManager(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tabLayout.setupWithViewPager(viewPager);

        //thay doi OptionMenu dua vao tab minh bam
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MenuId = tabLayout.getSelectedTabPosition();
                invalidateOptionsMenu();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("listClock", listClock);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.fragContain, clock_list.class, bundle );
//        ft.commit();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //clock
        if (item.getItemId() == R.id.CAdd){
            //them dong ho
            setTitle("");
            Intent intent = new Intent(MainActivity.this, Add_clock.class);

            startActivityForResult(intent,1);
        }

        //checkbox ko thay doi dc

        if (item.getItemId() == R.id.CEdit){
            //sua dong ho
            menu.clear();
            getMenuInflater().inflate(R.menu.edit, menu);

        }
        //sua dong ho
        if (item.getItemId() == R.id.IDel){

        }
        if (item.getItemId() == R.id.IFinish){



        }
        //them bao thuc
        if (item.getItemId() == R.id.AlAdd){

            Intent intent = new Intent(MainActivity.this, Add_alarm.class);

            startActivityForResult(intent,1);
        }
        if (item.getItemId() == R.id.AlEdit){
            //sua bao thuc
        }
        if (item.getItemId() == R.id.AlSettings){
            //cai dat bao thuc
            
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 ){
            Bundle b = data.getBundleExtra("clock");
            clock c =(clock) b.getSerializable("clock");
            clockmodel.setData(c);

        }

        if (resultCode == 2 ){

            Bundle b = data.getBundleExtra("alarm");
            alarm a = (alarm) b.getSerializable("alarm");
            alarmModel.setData(a);

        }


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;

        if(MenuId == 1) {
            setTitle("Báo thức");
            getMenuInflater().inflate(R.menu.alarm, menu);
        }
        else
            if (MenuId == 2) {
            setTitle("Bấm giờ");
            getMenuInflater().inflate(R.menu.timer, menu);
        }
        else  {
            setTitle("Đồng hồ");
            getMenuInflater().inflate(R.menu.clock, menu);
        }

        return super.onPrepareOptionsMenu(menu);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.clock, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }


}