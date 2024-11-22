package com.example.clockapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
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

/**
 * A fragment representing a list of Items.
 */
public class clockFragment extends Fragment {

    MyClockRecyclerViewAdapter clockAdapter;
    private Clockmodel vm;

    // TODO: Customize parameter argument names
    ArrayList<clock> listClock = new ArrayList<>();

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
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
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
        init();
    }

    private void init(){
        listClock.add(new clock(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"),new Locale("","VN") ) );
        listClock.add(new clock (TimeZone.getTimeZone("America/New_York"),new Locale("","US" )) );
    }
}