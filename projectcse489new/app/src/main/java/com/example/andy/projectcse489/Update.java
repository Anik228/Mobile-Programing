package com.example.andy.projectcse489;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {
    TableLayout t;
    TableRow t1, t2, t3, t4, t5, t6,t7,t8;
    EditText bookname, authorname, edition, gene, she, status, booksearch,copy,id;
    Button add;
    int coun = 0;
    String bookname1, key = "";
    String nameBOOK,Name,Author,Edition,Generation,Shelf,newcopynum,Activitytype,copynum;

    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    Button search, borrowlist, update, delete;
    Handler mHandler;
    public ListView listview;

    public List<bookdetails> studentList;
    public custom customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        databaseReference = FirebaseDatabase.getInstance().getReference("details");
        databaseReference1= FirebaseDatabase.getInstance().getReference("details");
        databaseReference2= FirebaseDatabase.getInstance().getReference("givebookdetails");

        bookname = findViewById(R.id.bname);

        authorname = findViewById(R.id.author);

        id = findViewById(R.id.stdid);


        edition = findViewById(R.id.edit);

        gene = findViewById(R.id.geni);

        she = findViewById(R.id.shel);

        status = findViewById(R.id.ty);

        booksearch = findViewById(R.id.booksearch);



        copy = findViewById(R.id.copy);



        search = findViewById(R.id.search);
        borrowlist = findViewById(R.id.seeborrowlist);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);



        search.setOnClickListener(view -> Search());
        delete.setOnClickListener(view -> Delete());
        update.setOnClickListener(view -> Update());


        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        //t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
       // t4.setVisibility(View.GONE);
        t5.setVisibility(View.GONE);
        t6.setVisibility(View.GONE);
        t7.setVisibility(View.GONE);
        t8.setVisibility(View.GONE);




//BOOK SEARCH THEN ADD textview

        // studentList=new ArrayList<>();

        //customAdapter=new custom(details.this,studentList);

        // listview=findViewById(R.id.listview);


    }


    public void Update() {

        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    String name = dataSnapshot1.child("name").getValue(String.class);

                    /*if(name.equals(nameBOOK))
                        dataSnapshot2.getRef().removeValue();*/
                    nameBOOK = booksearch.getText().toString().trim();
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


                        String newcopy=Integer.toString(t+1);

                        dataSnapshot1.getRef().child("copy").setValue(newcopy);

                        break;

                    }


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        String booksearchname = id.getText().toString().trim();


        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    String name = dataSnapshot1.child("SIDS").getValue(String.class);

                    if (name.equals(booksearchname)){
                        dataSnapshot1.getRef().removeValue();
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    break;}
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    public void Search() {


        coun = 0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //studentList.clear();

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    bookdetails student = dataSnapshot1.getValue(bookdetails.class);

                    String key = dataSnapshot1.getKey();

                    String authname = dataSnapshot1.child("auth").getValue(String.class);
                    copynum = dataSnapshot1.child("copy").getValue(String.class);
                    String edi = dataSnapshot1.child("edi").getValue(String.class);
                    String gene1 = dataSnapshot1.child("gen").getValue(String.class);
                    bookname1 = dataSnapshot1.child("name").getValue(String.class);

                    String shelf = dataSnapshot1.child("shel").getValue(String.class);
                    String type = dataSnapshot1.child("type").getValue(String.class);


                    String booksearchname = booksearch.getText().toString().trim();

                    if (bookname1.equals(booksearchname)) {

                        coun++;
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        //t4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        t6.setVisibility(View.VISIBLE);
                        t7.setVisibility(View.VISIBLE);
                        t8.setVisibility(View.VISIBLE);


                        //bookname,authorname,edition,gene,she,status
                        bookname.setText(bookname1);
                        authorname.setText(authname);
                        edition.setText(edi);

                        she.setText(shelf);
                        status.setText(type);
                        copy.setText(copynum);

                        break;


                    }


                    // student student1=new student(name,id);

                    //studentList.add(student);


                }

                if (coun == 0) {

                    Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_SHORT).show();
                }

//                    listview.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//new add


    public void Delete() {

        String booksearchname = booksearch.getText().toString().trim();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    String name = dataSnapshot1.child("name").getValue(String.class);

                    if (name.equals(booksearchname))
                        dataSnapshot1.getRef().removeValue();
                    Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                    break;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }




    }

