package com.example.andy.projectcse489;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<BorrowList> {
    private final Context context;
    private final ArrayList<BorrowList> values;
    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<BorrowList> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_event_row, parent, false);

        TextView borName = rowView.findViewById(R.id.tvBorName);
        TextView bookName = rowView.findViewById(R.id.tvBookName);

        borName.setText(values.get(position).borrower);
        bookName.setText(values.get(position).bookname);


        return rowView;
    }
}