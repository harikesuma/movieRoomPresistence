package com.example.room_presistance_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.room_presistance_crud.R;
import com.example.room_presistance_crud.room.Director;

import java.util.List;

public class DirectorAdapter extends BaseAdapter implements SpinnerAdapter {

    private LayoutInflater mInflater;
    private List<Director> mItems;

    public DirectorAdapter(Context context, List<Director> mItems) {
        mInflater = LayoutInflater.from(context);
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false);
        }
        Director item = (Director) getItem(position);
        TextView textView = view.findViewById(R.id.spinner_item);
        textView.setText(item.getDirector_name());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false);
        }
        Director item = (Director) getItem(position);
        TextView textView = view.findViewById(R.id.spinner_item);
        textView.setText(item.getDirector_name());
        return view;


    }
}
