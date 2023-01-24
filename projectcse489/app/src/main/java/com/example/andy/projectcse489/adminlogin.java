package com.example.andy.projectcse489;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {
    EditText username,password;
    Button login;
    String key="",s3="",s4="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.log);


        SharedPreferences sp=this.getSharedPreferences("admin_details",MODE_PRIVATE);

        s3=sp.getString("user-id","");
        s4=sp.getString("password","");

        if(s4.length()!=0){

            username.setText(s3);
            password.setText(s4);

        }

        login.setOnClickListener(view -> funcSave());


    }

    public void funcSave(){

        String id=username.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(pass.equals("admin")){
            if(s4.length()!=0){

                Intent in=new Intent(this,admin.class);
                startActivity(in);

            }
            if(pass.equals("admin")){


                Intent in=new Intent(this,admin.class);
                startActivity(in);
                SharedPreferences sp=this.getSharedPreferences("admin_details",MODE_PRIVATE);
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