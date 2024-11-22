package com.example.clockapp.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clockapp.ViewAdapter.MyalarmRecyclerViewAdapter;
import com.example.clockapp.R;
import com.example.clockapp.alarmReceiver;
import com.example.clockapp.placeholder.AlarmModel;
import com.example.clockapp.placeholder.alarm;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class alarmFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters

    private int mColumnCount = 1;

    private MyalarmRecyclerViewAdapter adapter;
    private AlarmModel vm;
    private ArrayList<alarm> alarmsList = new ArrayList<>();
    private AlarmManager alarmManager;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }

    }


    @Override
    public void onResume() {
        super.onResume();
        vm = new ViewModelProvider(requireActivity()).get(AlarmModel.class);
        alarm a = (alarm) vm.getData().getValue();

        if (a != null) {
            alarmsList.add(a);
            vm.setData(null);
            adapter.notifyDataSetChanged();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            Intent intent = new Intent(super.getContext(), alarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(super.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

            adapter = new MyalarmRecyclerViewAdapter(alarmsList,pendingIntent,alarmManager);
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

    }

}