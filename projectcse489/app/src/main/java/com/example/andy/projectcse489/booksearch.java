package com.example.andy.projectcse489;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class booksearch extends AppCompatActivity {
    TableLayout t;
    TableRow t1, t2, t3, t4, t5, t6;
    EditText bookname, authorname, edition, gene, she, status, booksearch;
    Button add;
    int coun=0;
    String bookname1,key="";

    DatabaseReference databaseReference;
    Button search,borrowlist,list,remind,stopremind;
    Handler mHandler;
    public ListView listview;

    public List<bookdetails> studentList;
    public custom customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);

        databaseReference = FirebaseDatabase.getInstance().getReference("details");

        bookname = findViewById(R.id.bname);

        authorname = findViewById(R.id.author);

        edition = findViewById(R.id.edit);

        gene = findViewById(R.id.geni);

        she = findViewById(R.id.shel);

        status = findViewById(R.id.ty);

        booksearch = findViewById(R.id.booksearch);

        list = findViewById(R.id.readingbookname);
        remind = findViewById(R.id.sremind);
        stopremind = findViewById(R.id.stremind);
        search = findViewById(R.id.search);
        borrowlist = findViewById(R.id.seeborrowlist);

        add = findViewById(R.id.add);

        search.setOnClickListener(view -> Search());
        borrowlist.setOnClickListener(view -> Seelist());
        list.setOnClickListener(view -> Seereadinglist());

        add.setOnClickListener(view -> save());

        remind.setOnClickListener(view -> startService());
        stopremind.setOnClickListener(view -> stopService());




        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        t5.setVisibility(View.GONE);
        t6.setVisibility(View.GONE);
        add.setVisibility(View.GONE);



//BOOK SEARCH THEN ADD textview

        // studentList=new ArrayList<>();

        //customAdapter=new custom(details.this,studentList);

        // listview=findViewById(R.id.listview);


    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Book Borrowed. Tap Here To Stop Reminder.");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }


    public void save(){

        String date="31/12/22";


        if(key.length()==0){
            key=bookname1+System.currentTimeMillis();
            System.out.println(key);
            String value=bookname1+"---"+date;
            KeyValueDB kvdb=new KeyValueDB(this);
            kvdb.insertKeyValue(key,value);
        }
        else{
            Toast.makeText(getApplicationContext(),"Data has been stored!",Toast.LENGTH_SHORT).show();
        }


    }

    public void Seelist(){

        Intent intent=new Intent(booksearch.this,details2.class);
        startActivity(intent);
    }

    public void Seereadinglist(){
        Intent intent=new Intent(booksearch.this,Upcoming_event.class);
        startActivity(intent);

    }


    public void Search() {



        coun=0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //studentList.clear();

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    bookdetails student = dataSnapshot1.getValue(bookdetails.class);

                    String key = dataSnapshot1.getKey();

                    String authname = dataSnapshot1.child("auth").getValue(String.class);
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
                        t4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        t6.setVisibility(View.VISIBLE);
                        add.setVisibility(View.VISIBLE);
                        //bookname,authorname,edition,gene,she,status
                        bookname.setText(bookname1);
                        authorname.setText(authname);
                        edition.setText(edi);
                        gene.setText(gene1);
                        she.setText(shelf);
                        status.setText(type);

                        break;


                    }





                }

                if(coun==0){

                    Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
