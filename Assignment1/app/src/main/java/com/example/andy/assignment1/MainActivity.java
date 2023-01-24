package com.example.andy.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,phone,phone1;
    Button cancel,save;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name1);
        mail = findViewById(R.id.Mail);
        phone = findViewById(R.id.phone);
        phone1 = findViewById(R.id.phone1);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> exit(view));



    }

    public void funcSave(){


        String name1=name.getText().toString().trim();
        String mail1=mail.getText().toString().trim();
        String phone2=phone.getText().toString().trim();
        String phone3=phone1.getText().toString().trim();

System.out.println(name1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anik);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        try {
            if (isValid(mail1)) {


                if (isValidMobileNo(phone2)) {
                    if (isValidMobileNo(phone3)) {
                        if(key.length()==0){
                        key = name1+ System.currentTimeMillis();
                        System.out.println(key);}
                        String value = name1 + "---" + mail + "---" + phone2 + "---" + phone3 + "---" + imageString;
                        KeyValueDB kvdb = new KeyValueDB(this);
                        kvdb.insertKeyValue(key, value);
                        Toast.makeText(getApplicationContext(), "Data Stored!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid phone number!",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid phone number!",Toast.LENGTH_SHORT).show();

                }
            }
            else{

                Toast.makeText(getApplicationContext(),"Invalid mail!",Toast.LENGTH_SHORT).show();
            }
        }

        //Arun1670647649123,AnikDey1670648275280,Anik1670657036004

        catch(Exception e){

            Toast.makeText(getApplicationContext(),"Error   Occur!",Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidMobileNo(String str)
    {

        return str.matches("^(?:\\+88|88)?(01[3-9]\\d{8})$");



}
    public void exit(View view){

        finish();
        System.exit(0);
    }

}