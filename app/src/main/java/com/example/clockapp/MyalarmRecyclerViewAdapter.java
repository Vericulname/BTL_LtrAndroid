package com.example.clockapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clockapp.databinding.FragmentAlarmItemBinding;
import com.example.clockapp.placeholder.alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link alarm}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyalarmRecyclerViewAdapter extends RecyclerView.Adapter<MyalarmRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<alarm> mValues;

    public MyalarmRecyclerViewAdapter(ArrayList<alarm>  items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAlarmItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("Test");
        holder.mContentView.setText("test" );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public alarm mItem;

        public ViewHolder(FragmentAlarmItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.textClock;
            mContentView = binding.switch1;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}