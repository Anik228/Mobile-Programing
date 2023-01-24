package edu.ewubd.anik2020160228;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, place, datetime, capacity, budget, email, phone, Description;
    Button save;
    Button cancel;
    RadioButton indoor,outdoor,online;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondlab_work);
        name = findViewById(R.id.fst);
        place = findViewById(R.id.second);
        datetime = findViewById(R.id.six);
        capacity = findViewById(R.id.seven);
        budget = findViewById(R.id.eight);
        email = findViewById(R.id.nine);
        phone = findViewById(R.id.ten);
        Description = findViewById(R.id.eleven);
        indoor = findViewById(R.id.third);
        outdoor= findViewById(R.id.fourth);
        online=findViewById(R.id.fifth);
        save = findViewById(R.id.fourteen);
        cancel = findViewById(R.id.twelve);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> funcCancel());

        String Ekey=getIntent().getStringExtra("EventKey");


        if(Ekey!=null){
            key=Ekey;
            loaddata(key);
        }




    }

    public void funcSave(){


        String title=name.getText().toString().trim();
        String place1=place.getText().toString().trim();
        String datetime1=datetime.getText().toString().trim();
        String capacity1=capacity.getText().toString().trim();
        String budget1=budget.getText().toString().trim();
        String mail=email.getText().toString().trim();
        String phone1=phone.getText().toString().trim();
        String description=Description.getText().toString().trim();

        String type="";
        if(indoor.isChecked()){
            type="indoor";
        }
        else if(outdoor.isChecked()){
            type="outdoor";

        }
        else if(online.isChecked()){
            type="online";

        }
        else
            type="notchecked";

        if(key.length()==0){
        key=title+System.currentTimeMillis();
        System.out.println(key);
        }
        else{
            Toast.makeText(getApplicationContext(),"Data already stored!",Toast.LENGTH_SHORT).show();
        }

        String value=title+"---"+place1+"---"+datetime1+"---"+capacity1+"---"+budget1+"---"+mail+"---"+phone1+"---"+description+"---"+type;

        /*KeyValueDB kvdb=new KeyValueDB(this);
        kvdb.insertKeyValue(key,value);*/
        String[] keys = {"action", "id", "semester", "key", " event"};
        String[] values = {"backup", "2020-1-60-228", "2022-3", key,value};
        httpRequest(keys, values);

//cse4891669278469915

       /* System.out.println(name.getText().toString().trim());
        System.out.println(place.getText().toString().trim());
        System.out.println(datetime.getText().toString().trim());
        System.out.println(capacity.getText().toString().trim());
        System.out.println(budget.getText().toString().trim());
        System.out.println(email.getText().toString().trim());
        System.out.println(phone.getText().toString().trim());
        System.out.println(Description.getText().toString().trim());*/



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
                        //updateEventListByServerData(data);
                        System.out.println("Data stored");
                        System.out.println(data);
                        Intent i = new Intent(MainActivity.this, Upcoming_event.class);

                        startActivity(i);
                    }
                    catch(Exception e){

                        e.printStackTrace();
                    }
                }
             }

        }.execute();

    }






    public void funcCancel(){

        finish();
    }
    public void loaddata(String key){
        KeyValueDB kv=new KeyValueDB(this);
        String v=kv.getValueByKey(key);
        if(v!=null) {
            String values[] = v.split("---");
            for (int i = 0; i < values.length; i++) {

                System.out.println(values[i]);//settext
            }
            name.setText(values[0]);
            place.setText(values[1]);
            datetime.setText(values[2]);
            capacity.setText(values[3]);
            budget.setText(values[4]);
            email.setText(values[5]);
            phone.setText(values[6]);
            Description.setText(values[7]);



            System.out.println(values[8]);
            if(values[8].equals("indoor")){
                //System.out.println("Not working");
                indoor.setChecked(true);

            }

            else if(values[8].equals("indoor")){
                outdoor.setChecked(true);

            }

            else if(values[8].equals("indoor")){

                online.setChecked(true);
            }



        }

    }
}