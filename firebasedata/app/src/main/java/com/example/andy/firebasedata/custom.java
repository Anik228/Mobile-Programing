package com.example.andy.firebasedata;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class custom extends ArrayAdapter<student> {
    private Activity context;
    private List<student> studentList;

    public custom(Activity context , List<student> studentList) {
        super(context, R.layout.sample_layout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);

        TextView t1=view.findViewById(R.id.Name);
        TextView t2=view.findViewById(R.id.ID);
        student student1=new student();

        student student=studentList.get(position);

        t1.setText("Name: "+student.getName());
        t2.setText("ID: "+student.getId());




        return view;
    }
}
