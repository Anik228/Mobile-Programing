package com.example.andy.projectcse489;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    EditText id1,password1;
    Button adlog,log;
    String key="",s3="",s4="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        id1 = findViewById(R.id.id);
        password1 = findViewById(R.id.password);

        SharedPreferences sp=this.getSharedPreferences("user_details",MODE_PRIVATE);

         s3=sp.getString("user-id","");
         s4=sp.getString("password","");

        if(s4.length()!=0){

            id1.setText(s3);
            password1.setText(s4);

        }



        adlog = findViewById(R.id.adminlogin);
        log = findViewById(R.id.login);
        log.setOnClickListener(view -> funcSave());

        adlog.setOnClickListener(view -> funadmin());

       // adlog.setOnClickListener(view -> exit(view));



    }

    public void funadmin(){

        Intent in=new Intent(this,adminlogin.class);
        startActivity(in);


    }

    public void funcSave(){

        String id=id1.getText().toString().trim();
        String pass=password1.getText().toString().trim();
        String[] fieldValues = id.split("-");
        System.out.println(id);
        System.out.println(pass);
        int number = Integer.parseInt(fieldValues[0]);
        System.out.println(number);
        System.out.println(id1.length());

        if(number>2015 && number<2023 && id1.length()==13 && fieldValues[0].length()==4){
            if(s4.length()!=0){

                Intent in=new Intent(this,booksearch.class);
                startActivity(in);

            }
            if(pass.equals("ewulibrary")){

                Intent in=new Intent(this,booksearch.class);
                startActivity(in);

                SharedPreferences sp=this.getSharedPreferences("user_details",MODE_PRIVATE);
                SharedPreferences .Editor e=sp.edit();
                e.putString("user-id",id);
                e.putString("password",pass);
                e.apply();

                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Invalid Pass!", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid Id or Pass!", Toast.LENGTH_SHORT).show();
        }


    }



}