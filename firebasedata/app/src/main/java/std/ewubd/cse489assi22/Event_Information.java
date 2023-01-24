package std.ewubd.cse489assi22;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;;
import android.widget.Toast;

public class Event_Information extends AppCompatActivity {
    EditText borrower, bookname, borrowdate, returndate;
    Button save;
    Button cancel;
    //    String key="";
    private String key = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_information);
        Intent i = getIntent();
        if(i.hasExtra("EventKey")) {
            key = i.getStringExtra("EventKey");
        }

        borrower = findViewById(R.id.borName);
        bookname = findViewById(R.id.bookName);
        borrowdate = findViewById(R.id.borDate);
        returndate = findViewById(R.id.retDate);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> funcCancel());
        String Ekey=getIntent().getStringExtra("EventKey");
        if(Ekey!=null){
            key=Ekey;
            loaddata(key);
        }
    }
    public void funcSave(){
        String borrower1=borrower.getText().toString().trim();
        String bookname1=bookname.getText().toString().trim();
        String borrowdate1=borrowdate.getText().toString().trim();
        String returndate1=returndate.getText().toString().trim();

        if(key.length()==0){
            key=borrower1+System.currentTimeMillis();
            System.out.println(key);
        }
        else{
            Toast.makeText(getApplicationContext(),"Data has been stored!",Toast.LENGTH_SHORT).show();
        }
        String value=borrower1+"---"+bookname1+"---"+borrowdate1+"---"+returndate1;

        KeyValueDB kvdb=new KeyValueDB(this);
        kvdb.insertKeyValue(key,value);
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
            borrower.setText(values[0]);
            bookname.setText(values[1]);
            borrowdate.setText(values[2]);
            returndate.setText(values[3]);
        }
    }
}