package com.example.andy.projectcse489;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin extends AppCompatActivity {
    EditText bookname,author,edition,generation,shelf,Copy;
    RadioButton activity1,activity2,activity3;
    Button save,status,givebook,borrowlist,update;
    DatabaseReference databaseReference;

    String Activitytype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler mHandler;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        databaseReference= FirebaseDatabase.getInstance().getReference("details");

        bookname=findViewById(R.id.name);
        author=findViewById(R.id.authname);
        edition=findViewById(R.id.edition);
        generation=findViewById(R.id.gen);
        givebook=findViewById(R.id.give);
        borrowlist=findViewById(R.id.list);
        shelf=findViewById(R.id.shelf);
        Copy=findViewById(R.id.copy);
        activity1=findViewById(R.id.available);
        activity2=findViewById(R.id.notavailable);

        save=findViewById(R.id.save);
        status=findViewById(R.id.status);
        update=findViewById(R.id.update);


        save.setOnClickListener(view -> Save());
        status.setOnClickListener(view -> Status());
        givebook.setOnClickListener(view -> Givebook());
        borrowlist.setOnClickListener(view -> list());
        update.setOnClickListener(view -> Update());



    }


    public void Update(){

        Intent intent=new Intent(admin.this,Update.class);
        startActivity(intent);

    }

    public void list(){

        Intent intent=new Intent(admin.this,details1.class);
        startActivity(intent);

    }

    public void Givebook(){

        Intent intent=new Intent(admin.this,givebook.class);
        startActivity(intent);

    }

    public void Status(){

        Intent intent=new Intent(admin.this,details.class);
        startActivity(intent);

    }


    public void Save(){

        Handler mHandler = new Handler();

        new Thread(new Runnable(){

            public void run(){

                String Name=bookname.getText().toString().trim();
                String Author=author.getText().toString().trim();
                String Edition=edition.getText().toString().trim();
                String Generation=generation.getText().toString().trim();
                String Shelf=shelf.getText().toString().trim();
                String copynum=Copy.getText().toString().trim();
               // long delay = Long.parseLong(copynum);

                if(activity1.isChecked()){

                     Activitytype="Read only";
                }
                else if(activity2.isChecked()){

                     Activitytype="Read & Borrowable";
            }



                String key=databaseReference.push().getKey();

                bookdetails details1=new bookdetails(Name,Author,Edition,Generation,Shelf,copynum,Activitytype);

                databaseReference.child(key).setValue(details1);


                mHandler.post(new Runnable(){

                    public void run(){

                        Toast.makeText(admin.this,"Data stored", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).start();








    }


}