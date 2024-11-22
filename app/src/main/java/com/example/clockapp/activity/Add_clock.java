package com.example.clockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.clockapp.R;
import com.example.clockapp.placeholder.clock;

import java.util.Locale;
import android.icu.util.TimeZone;
import android.widget.SearchView;
import android.widget.TextView;

public class Add_clock extends AppCompatActivity implements SearchView.OnQueryTextListener {


    ListView lv;
    TextView bt;
    SearchView sv;
    ArrayAdapter<?> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_clock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.lvTimeZone);
        sv = findViewById(R.id.searchView);
        bt = findViewById(R.id.btCancel);
        sv.setOnQueryTextListener(this);
        String[] tz = TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.CANONICAL_LOCATION, null, null).toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tz);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TimeZone tz = TimeZone.getTimeZone(adapterView.getItemAtPosition(i).toString());
                clock clock = new clock(tz, new Locale("",tz.getID().split("/")[1] ));
                Intent intent = getIntent();
                Bundle b = new Bundle();
                b.putSerializable("clock",clock);
                intent.putExtra("clock",b);
                setResult(1,intent);
                finish();
            }
        } );
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return false;
    }
}