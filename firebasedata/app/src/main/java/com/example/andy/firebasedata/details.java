package com.example.andy.firebasedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class details extends AppCompatActivity {
    public ListView listview;
    DatabaseReference databaseReference;
    public List<student> studentList;
    public custom customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference= FirebaseDatabase.getInstance().getReference("students");

        studentList=new ArrayList<>();

        customAdapter=new custom(details.this,studentList);

        listview=findViewById(R.id.listview);

    }
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                studentList.clear();

                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    student student=dataSnapshot1.getValue(student.class);



                    String key = dataSnapshot1.getKey();

                    String id = dataSnapshot1.child("id").getValue(String.class);
                    String name = dataSnapshot1.child("name").getValue(String.class);

                   // student student1=new student(name,id);

                    studentList.add(student);

                    /*System.out.println(key);
                    System.out.println(id);
                    System.out.println(name);*/
                   // System.out.println(studentList);

                }

                listview.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

}