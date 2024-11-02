package com.example.clockapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.clockapp.placeholder.clock;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A fragment representing a list of Items.
 */
public class clockFragment extends Fragment {

    // TODO: Customize parameter argument names
    ArrayList<clock> listClock = new ArrayList<>();

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
            recyclerView.setAdapter(new MyClockRecyclerViewAdapter(listClock));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        listClock.add(new clock(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"),new Locale("vi","VN") ) );
        listClock.add(new clock (TimeZone.getTimeZone("America/New_York"),new Locale("en","US" )) );
    }
}