package com.example.andy.projectcse489;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class custom1 extends ArrayAdapter<give> {
    private Activity context;
    private List<give> studentList;

    public custom1(Activity context , List<give> studentList) {
        super(context, R.layout.sample_layout1,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.sample_layout1,null,true);

        TextView t1=view.findViewById(R.id.Name);
        TextView t2=view.findViewById(R.id.stdn);
        TextView t3=view.findViewById(R.id.stdid);
        TextView t4=view.findViewById(R.id.date);



        give student=studentList.get(position);

        t1.setText("Book name   :"+student.getNaB());
        t2.setText("Student name:"+student.getname());
        t3.setText("ID          :"+student.getIDS());
        t4.setText("Date        :"+student.getDa());


        return view;
    }
}
