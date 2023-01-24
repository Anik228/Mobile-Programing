package com.example.andy.projectcse489;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class custom extends ArrayAdapter<bookdetails> {
    private Activity context;
    private List<bookdetails> studentList;

    public custom(Activity context , List<bookdetails> studentList) {
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
        TextView t2=view.findViewById(R.id.auth);
        TextView t3=view.findViewById(R.id.edition);
        TextView t4=view.findViewById(R.id.genre);
        TextView t5=view.findViewById(R.id.shelf);
        TextView t6=view.findViewById(R.id.Type);
        TextView t7=view.findViewById(R.id.copy);

        bookdetails student1=new bookdetails();

        bookdetails student=studentList.get(position);

        t1.setText("Book name       : "+student.getName());
        t2.setText("Author name     : "+student.getAuth());
        t3.setText("Edition         : "+student.getEdi());
        t4.setText("Genre           : "+student.getGen());
        t5.setText("Shelf no        : "+student.getShel());
        t6.setText("Book Type       : "+student.getType());
        t7.setText("Copy Available  : "+student.getCopy());

        return view;
    }
}
