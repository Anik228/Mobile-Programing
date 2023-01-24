package com.example.andy.projectcse489;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class givebook extends AppCompatActivity {
    EditText bookname,studentname,studentid,date,searchbook;
    TextView std,bna,sid,da;
    Button give,search;
    int coun=0;
    DatabaseReference databaseReference, databaseReference1,databaseReference2;
    String nameBOOK,Name,Author,Edition,Generation,Shelf,newcopynum,Activitytype,copynum;


    //String Activitytype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler mHandler;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book);
        databaseReference= FirebaseDatabase.getInstance().getReference("givebookdetails");
        databaseReference1= FirebaseDatabase.getInstance().getReference("details");

        searchbook=findViewById(R.id.searchbook);

        bookname=findViewById(R.id.BookN);
        studentname=findViewById(R.id.stdname);
        studentid=findViewById(R.id.stdid);
        date=findViewById(R.id.date);
        std=findViewById(R.id.s);
        bna=findViewById(R.id.b);
        sid=findViewById(R.id.sid);
        da=findViewById(R.id.d);

        search=findViewById(R.id.search);
        give=findViewById(R.id.borrow);



        bookname.setVisibility(View.GONE);
        studentname.setVisibility(View.GONE);
        studentid.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        give.setVisibility(View.GONE);

        std.setVisibility(View.GONE);
        bna.setVisibility(View.GONE);
        da.setVisibility(View.GONE);
        sid.setVisibility(View.GONE);


        search.setOnClickListener(view -> Search());




        give.setOnClickListener(view -> Save());






        /*Intent intent = new Intent(givebook.this, update.class);
        Bundle extras = new Bundle();
        extras.putString("Book_name",Name);
        extras.putString("Author_name",Author);
        extras.putString("Edition",Edition);
        extras.putString("Gen",Generation);
        extras.putString("Shelf",Shelf);
        extras.putString("copynum",newcopynum);
        extras.putString("ActivityType",Activitytype);
        intent.putExtras(extras);
        startActivity(intent);*/
        System.out.println(Name);


    }



public void Search(){



    databaseReference1.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            //studentList.clear();

            for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                bookdetails student = dataSnapshot1.getValue(bookdetails.class);

                String key = dataSnapshot1.getKey();

                String authname = dataSnapshot1.child("auth").getValue(String.class);
                String edi = dataSnapshot1.child("edi").getValue(String.class);
                String gene1 = dataSnapshot1.child("gen").getValue(String.class);
                String bookname1 = dataSnapshot1.child("name").getValue(String.class);
                String shelf = dataSnapshot1.child("shel").getValue(String.class);
                String type = dataSnapshot1.child("type").getValue(String.class);


                String booksearchname = searchbook.getText().toString().trim();



                if (bookname1.equals(booksearchname)) {

                    searchbook.setText(type);

                    if(type.equals("Read & Borrowable")){
                        Toast.makeText(givebook.this,"Borrowable", Toast.LENGTH_LONG).show();
                        bookname.setVisibility(View.VISIBLE);
                        bookname.setText(booksearchname);
                        studentname.setVisibility(View.VISIBLE);
                        studentid.setVisibility(View.VISIBLE);
                        date.setVisibility(View.VISIBLE);
                        give.setVisibility(View.VISIBLE);
                        std.setVisibility(View.VISIBLE);
                        bna.setVisibility(View.VISIBLE);
                        da.setVisibility(View.VISIBLE);
                        sid.setVisibility(View.VISIBLE);

                    }
                    else
                        Toast.makeText(givebook.this,"Not Borrowable", Toast.LENGTH_LONG).show();
                    break;


                }



            }


//                    listview.setAdapter(customAdapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });




}



    public void Save(){




        Handler mHandler = new Handler();

        new Thread(new Runnable(){

            public void run(){

                nameBOOK=bookname.getText().toString().trim();
                String nameSTD=studentname.getText().toString().trim();
                String nameID=studentid.getText().toString().trim();
                String Dat=date.getText().toString().trim();





                String key=databaseReference.push().getKey();

                give givedetails1=new give(nameBOOK,nameSTD,nameID,Dat);

                databaseReference.child(key).setValue(givedetails1);


                mHandler.post(new Runnable(){

                    public void run(){

                        Toast.makeText(givebook.this,"Data stored", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).start();




        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    String name = dataSnapshot1.child("name").getValue(String.class);

                    /*if(name.equals(nameBOOK))
                        dataSnapshot2.getRef().removeValue();*/
                    if(name.equals(nameBOOK)){

                        Author = dataSnapshot1.child("auth").getValue(String.class);
                        copynum = dataSnapshot1.child("copy").getValue(String.class);
                        Edition = dataSnapshot1.child("edi").getValue(String.class);
                        Generation = dataSnapshot1.child("gen").getValue(String.class);
                        Name = dataSnapshot1.child("name").getValue(String.class);
                        Shelf = dataSnapshot1.child("shel").getValue(String.class);
                        Activitytype = dataSnapshot1.child("type").getValue(String.class);


                        System.out.println(copynum);
                       // dataSnapshot1.getRef().removeValue();
                        String key=databaseReference1.push().getKey();


                        int t=Integer.parseInt(copynum);


                        String newcopy=Integer.toString(t-1);

                        dataSnapshot1.getRef().child("copy").setValue(newcopy);

                        break;

                    }


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}
