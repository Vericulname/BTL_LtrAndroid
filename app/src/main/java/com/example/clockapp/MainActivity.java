package com.example.clockapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.clockapp.placeholder.clock;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<clock> listClock = new ArrayList<>();
    private int MenuId = 0;
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
        // nen import androidx

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
            Intent intent = new Intent(MainActivity.this, Add_clock.class);
            ActivityResultLauncher<Intent> new_clock = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {

                        }
                    });
        }
        if (item.getItemId() == R.id.CEdit){
            //sua dong ho
        }
        if (item.getItemId() == R.id.AlAdd){
            //them bao thuc
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(MenuId == 1) {
            setTitle("báo thức");
            getMenuInflater().inflate(R.menu.alarm, menu);
        }
        else {
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