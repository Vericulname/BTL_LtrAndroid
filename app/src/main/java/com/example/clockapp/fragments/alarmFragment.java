package com.example.clockapp.fragments;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.TimeZone;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.clockapp.ViewAdapter.MyalarmRecyclerViewAdapter;
import com.example.clockapp.R;
import com.example.clockapp.activity.Add_alarm;
import com.example.clockapp.alarmReceiver;
import com.example.clockapp.interfaces.RecycleItemClick;
import com.example.clockapp.placeholder.AlarmModel;
import com.example.clockapp.placeholder.alarm;
import com.example.clockapp.placeholder.clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 */
public class alarmFragment extends Fragment implements RecycleItemClick {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters

    private int mColumnCount = 1;

    private static MyalarmRecyclerViewAdapter adapter;
    private AlarmModel vm;
    private static ArrayList<alarm> alarmsList = new ArrayList<>();
    private static AlarmManager alarmManager;
    private static SQLiteDatabase db;
    private int alarm_pos = -1;
    private static RecyclerView recyclerView;


    public alarmFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static alarmFragment newInstance(int columnCount) {
        alarmFragment fragment = new alarmFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    public static void editMode(){

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            cb.setVisibility(View.VISIBLE);
        }
    }
    public static void delete(){
        //tim vi tri cua dong ho trong danh sach
        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            if (cb.isChecked()){
                pos.add(i);
                cb.setChecked(false);
            }

        }

        //xoa dong ho

        for (int i: pos)  {
            alarmsList.remove(i);
            db.delete("alarm", "alarm_id=?", new String[]{String.valueOf(i)});

        }
        adapter.notifyDataSetChanged();
        pos.clear();
    }
    public static void FinishEdit(){
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            cb.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getActivity().openOrCreateDatabase("clockapp.db", Context.MODE_PRIVATE, null);


    }


    @Override
    public void onResume() {
        super.onResume();
        vm = new ViewModelProvider(requireActivity()).get(AlarmModel.class);
        alarm a = (alarm) vm.getData().getValue();
        alarm_pos = vm.getPos();

        if (a!= null && alarm_pos != -1){
            alarmsList.set(alarm_pos,a);
            vm.setData(null);
            vm.SetPos(-1);
            a = null;
        }

        if (a != null) {
            alarmsList.add(a);
            vm.setData(null);
        }

        adapter.notifyDataSetChanged();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            alarmFragment.recyclerView = recyclerView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            Intent intent = new Intent(super.getContext(), alarmReceiver.class);

            alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);


            adapter = new MyalarmRecyclerViewAdapter(alarmsList,alarmManager,super.getContext(),intent,this);
            recyclerView.setAdapter(adapter);

        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    //TODO: lay du lieu tu cai j do ve bao thuc
    private void init(){
        alarmsList.clear();
        String sql = "select * from alarm";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            ArrayList<Integer> days = new ArrayList<>();

            for (char i:
            c.getString(2).toCharArray()) {
                if (i != ',')
                    days.add(Integer.parseInt(String.valueOf(i)));
            }
            alarmsList.add(new alarm(
                    c.getString(1),
                    days,
                    c.getLong(3)
            ));
            c.moveToNext();
        }
        c.close();

    }

    @Override
    public void onRecycleItemClick(int pos) {
//        if(!IsEditMode){
        Intent editAlarm = new Intent(super.getContext(), Add_alarm.class);
//        Bundle b = new Bundle();
//        b.putInt("alarm_pos",pos);
        editAlarm.putExtra("alarm_pos",pos);

        this.startActivityForResult(editAlarm,2);
//        }
//        else {
//
//        }
    }

}