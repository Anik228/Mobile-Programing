package com.example.andy.firebasedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,id,deletedata;
    Button save,load,delete;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler mHandler;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("students");

        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        deletedata=findViewById(R.id.deletedata);
        save=findViewById(R.id.save);
        load=findViewById(R.id.load);
        delete=findViewById(R.id.delete);


        save.setOnClickListener(view -> Save());
        load.setOnClickListener(view -> Load());

        delete.setOnClickListener(view -> Delete());


    }
    public void Save(){


        Handler mHandler = new Handler();

        new Thread(new Runnable(){

            public void run(){

                String Name=name.getText().toString().trim();
                String Id=id.getText().toString().trim();
                String key=databaseReference.push().getKey();

                student student=new student(Name,Id);

                databaseReference.child(key).setValue(student);


                //Handler mHandler = new Handler();
                mHandler.post(new Runnable(){

                    public void run(){

                        Toast.makeText(MainActivity.this,"Data stored", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).start();



       /* String Name=name.getText().toString().trim();
       String Id=id.getText().toString().trim();
       String key=databaseReference.push().getKey();

       student student=new student(Name,Id);

       databaseReference.child(key).setValue(student);*/

      // Toast.makeText(MainActivity.this,"Data stored", Toast.LENGTH_LONG).show();


    }

    public void Load(){

        Intent intent=new Intent(MainActivity.this,details.class);
        startActivity(intent);


    }


    public void Delete(){

        String data=deletedata.getText().toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    String name = dataSnapshot1.child("name").getValue(String.class);

                    if(name.equals(data))
                    dataSnapshot1.getRef().removeValue();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    }