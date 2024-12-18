package com.example.clockapp.ViewAdapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;
import com.example.clockapp.placeholder.clock;
import com.example.clockapp.databinding.FragmentClockItemsBinding;

import java.util.ArrayList;

/**
 
 * TODO: Replace the implementation with code for your data type.
 */
public class MyClockRecyclerViewAdapter extends RecyclerView.Adapter<MyClockRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<clock> mValues;

    public MyClockRecyclerViewAdapter( ArrayList<clock> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentClockItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final MyClockRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mClock.setTimeZone(mValues.get(position).getTimeZone().getID());
        holder.mContentView.setText( mValues.get(position).getLocation().getDisplayCountry().toLowerCase());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextClock mClock;
        public final TextView mContentView;
        public clock mItem;

        public ViewHolder( FragmentClockItemsBinding binding) {
            super(binding.getRoot());
            mClock = binding.textClock;
            mContentView = binding.textView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}