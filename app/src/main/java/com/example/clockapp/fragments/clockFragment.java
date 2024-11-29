package com.example.clockapp.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.clockapp.ViewAdapter.MyClockRecyclerViewAdapter;
import com.example.clockapp.R;
import com.example.clockapp.placeholder.Clockmodel;
import com.example.clockapp.placeholder.clock;

import java.util.ArrayList;
import java.util.Locale;
import android.icu.util.TimeZone;
import android.widget.CheckBox;

/**
 * A fragment representing a list of Items.
 */
public class clockFragment extends Fragment {

    static MyClockRecyclerViewAdapter clockAdapter;
    private Clockmodel vm;
    static SQLiteDatabase db;
    public static Menu menu;
    private static ArrayList<Integer> pos = new ArrayList<>();

    private static RecyclerView recyclerView;


    // TODO: Customize parameter argument names
    static ArrayList<clock> listClock = new ArrayList<>();

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    public clockFragment() {

    }
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static clockFragment newInstance(int columnCount) {
        clockFragment fragment = new clockFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getActivity().openOrCreateDatabase("clockapp.db", Context.MODE_PRIVATE, null);

    }

    public static void editMode(){

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            cb.setVisibility(View.VISIBLE);
        }
    }
    public static void delete(){
        //tim vi tri cua dong ho trong danh sach
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            if (cb.isChecked()){
                pos.add(i);
                cb.setChecked(false);
            }

        }

        //xoa dong ho

        for (int i: pos)  {
            listClock.remove(i);
            db.delete("clock", "clock_id=?", new String[]{String.valueOf(i)});

        }
        clockAdapter.notifyDataSetChanged();
        pos.clear();
    }
    public static void FinishEdit(){
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            CheckBox cb = recyclerView.getChildAt(i).findViewById(R.id.Cb);
            cb.setVisibility(View.GONE);
        }
    }


    //cap nhat danh sach clock
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resume");
        //lay dl
        vm = new ViewModelProvider(requireActivity()).get(Clockmodel.class);

        clock c = (clock) vm.getData().getValue();
        if (c != null){
            listClock.add(c);
            vm.setData(null);
            clockAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_clock_list, container, false);

        if (getArguments() != null) {
            listClock= (ArrayList<clock>) getArguments().getSerializable("listClock");
        }
        // Set the adapter
        init();
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            clockFragment.recyclerView = recyclerView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            clockAdapter = new MyClockRecyclerViewAdapter(listClock);

            recyclerView.setAdapter(clockAdapter);
        }

        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void init(){

        listClock.clear();
        String sql = "select * from clock";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            listClock.add(new clock(
                    TimeZone.getTimeZone(c.getString(2)),
                    new Locale("",c.getString(1) )));
            c.moveToNext();
        }
        c.close();

    }
}