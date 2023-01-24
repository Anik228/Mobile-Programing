package com.example.andy.sqldemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Upcoming_event extends AppCompatActivity {
    Button btnExit;
    ListView lvBorrowList;
    ArrayList<BorrowList> blists;
    CustomEventAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {

        // initialize list-reference by ListView object defined in XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_event);
        btnExit=findViewById(R.id.btnExit);
        btnExit.setOnClickListener(view -> finish());
        lvBorrowList = findViewById(R.id.lvBorrowList);
        // load blists from database if there is any
        loadData();
    }
    private void loadData(){
        blists = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //blists = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("---");

            String borrower = fieldValues[0];
            String bookname= fieldValues[1];
            String borrowdate = fieldValues[2];
            String returndate = fieldValues[3];
            BorrowList e = new BorrowList(key, borrower, bookname, borrowdate, returndate);
            blists.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, blists);
        lvBorrowList.setAdapter(adapter);
        // handle the click on an event-list item
        lvBorrowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(Upcoming_event.this, Event_Information.class);
                i.putExtra("EventKey", blists.get(position).key);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvBorrowList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?>parent, View view, int position, long id) {
                String message = "Do you want to delete record of "+blists.get(position).borrower+"?";
                showDialog(message, "Delete Event",blists.get(position).key);
                return true;
            }
        });
    }
    private void showDialog(String message, String borrower1, String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(borrower1);
        builder.setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
//           Util.getInstance().deleteByKey(MainActivity.this, key);
                KeyValueDB db = new KeyValueDB(getApplicationContext());
                db.deleteDataByKey(key);
                dialog.cancel();
                loadData();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }

        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

