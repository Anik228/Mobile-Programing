package edu.ewubd.anik2020160228;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Upcoming_event extends AppCompatActivity {


    ListView lvEvents;
    Button create;
    ArrayList<Event> events;
    CustomEventAdapter adapter;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        // initialize list-reference by ListView object defined in XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_event);

        lvEvents = findViewById(R.id.lvEvents);
        events = new ArrayList<>();

        create=findViewById(R.id.create);

        create.setOnClickListener(view -> Create(view));


        // handle the click on an event-list item
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(Upcoming_event.this, MainActivity.class);
                i.putExtra("EventKey", events.get(position).key);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).name +" ?";
                System.out.println(message);
                showDialog(message, "Delete Event", events.get(position).key);
                return true;
            }
        });
// load events from database if there is any
        String[] keys = {"action", "id", "semester"};
        String[] values = {"restore", "2020-1-60-228", "2022-3"};
        httpRequest(keys, values);
    }
    public void httpRequest(final String keys[],final String values[]){

        new AsyncTask<Void,Void,String>(){
            protected String doInBackground(Void... param){
                try{

                    List<NameValuePair> params =new ArrayList<>();
                    for(int i=0;i<keys.length;i++){
                        params.add(new BasicNameValuePair(keys[i],values[i]));
                    }
                    String data =JSONParser.getInstance().makeHttpRequest( "https://muthosoft.com/univ/cse489/index.php","POST",params);
                    return data;
                }
                catch(Exception ex){

                    ex.printStackTrace();
                }
                return null;

            }
            protected void onPostExecute(String data){
                if(data!=null){
                    try{
                        updateEventListByServerData(data);
                    }
                    catch(Exception e){

                        e.printStackTrace();
                    }
                }
            }

        }.execute();

    }

    public void updateEventListByServerData(String data) throws JSONException {
        JSONObject jo = new JSONObject(data);
        if(jo.has("events")){
            JSONArray ja = jo.getJSONArray("events");
            for(int i=0; i<ja.length(); i++){
                JSONObject event = ja.getJSONObject(i);
                System.out.println("from UEJ 119");
                System.out.println(event);
                String eventKey = event.getString("key");
                String eventValue = event.getString("value");

                String fieldValues[] = eventValue.split("---");

                String name = fieldValues[0];
                String place= fieldValues[1];
                String dateTime = fieldValues[2];
                String capacity = fieldValues[3];
                String budget = fieldValues[4];
                String email = fieldValues[5];
                String phone = fieldValues[6];
                String description = fieldValues[7];
                String eventType = fieldValues[8];
                Event e = new Event(eventKey, name, place, dateTime, capacity, budget, email, phone, description, eventType);
                System.out.println(e);
                events.add(e);
            }
            adapter = new CustomEventAdapter(this, events);
            lvEvents.setAdapter(adapter);

        }
    }
    private void loadData(){
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("---");

            String name = fieldValues[0];
            String place= fieldValues[1];
            String dateTime = fieldValues[2];
            String capacity = fieldValues[3];
            String budget = fieldValues[4];
            String email = fieldValues[5];
            String phone = fieldValues[6];
            String description = fieldValues[7];
            String eventType = fieldValues[8];
            Event e = new Event(key, name, place, dateTime, capacity, budget, email, phone, description, eventType);
            events.add(e);
        }
        db.close();





    }


    private void showDialog(String message,String title,String key){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(false)
        .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id){
               KeyValueDB db=new KeyValueDB(getApplicationContext());
               db.deleteDataByKey(key);

                dialog.cancel();
//                loadData();

                adapter.notifyDataSetChanged();
            }


        })

                .setNegativeButton("No" ,new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog,int id){
                        dialog.cancel();
                    }
                });

        AlertDialog alert=builder.create();
        alert.show();
    }

    public void Create(View view){

        Intent i = new Intent(Upcoming_event.this, MainActivity.class);

        startActivity(i);

    }



}


