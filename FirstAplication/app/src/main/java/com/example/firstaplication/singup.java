package com.example.firstaplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class singup extends AppCompatActivity {
    EditText name,mail,phone,userID,password,repassword;
    Button Exit,Go;
    TextView haveaccount,login;
    CheckBox checkbox1,checkbox2;
    TableRow row1,row2,row3,row4,row5,row6;
    String s1,s2,s3,s4;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences sp=this.getSharedPreferences("user_details",MODE_PRIVATE);

        s1=sp.getString("REM_USER", "");
        s2=sp.getString("REM_PASS","");

        s3=sp.getString("user-id","");
        s4=sp.getString("password","");

        if(s3.length()>0){

           if(s1.equals("YES") && s2.equals("YES")){

                setContentView(R.layout.lab4_layout);

                //System.out.println(s1);
                //System.out.println(s2);
                System.out.println("IF work");
                row1=findViewById(R.id.Namerow);
                row2=findViewById(R.id.Emailrow);
                row3=findViewById(R.id.Phonerow);
                row5=findViewById(R.id.Repassrow);
                row1.setVisibility(View.INVISIBLE);
                row2.setVisibility(View.INVISIBLE);
                row3.setVisibility(View.INVISIBLE);
                row5.setVisibility(View.INVISIBLE);
                userID = findViewById(R.id.Userid);
                password = findViewById(R.id.Pass);
                checkbox1 = findViewById(R.id.rememberuser);
                checkbox2 = findViewById(R.id.rememberpass);
                haveaccount= findViewById(R.id.Already);
                login = findViewById(R.id.Login);
                haveaccount.setVisibility(View.INVISIBLE);
                login.setVisibility(View.INVISIBLE);
                checkbox1.setVisibility(View.GONE);
                checkbox2.setVisibility(View.GONE);
                userID.setText(s3);
                password.setText(s4);
               Go = findViewById(R.id.Go);
               Go.setOnClickListener(view -> go1(view));
               Exit = findViewById(R.id.exit);
               Exit.setOnClickListener(view -> funcCancel(view));

           }
            else{
                setContentView(R.layout.lab4_layout);

               /* row1.setVisibility(View.INVISIBLE);
                row2.setVisibility(View.INVISIBLE);
                row5.setVisibility(View.INVISIBLE);
                userID.setText(s3);
                userID = findViewById(R.id.Userid);
                password = findViewById(R.id.Pass);*/

            }

           // setContentView(R.layout.lab4_layout);



        }

        else {
            setContentView(R.layout.lab4_layout);
            name = findViewById(R.id.Name);
            mail = findViewById(R.id.Email);
            phone = findViewById(R.id.Phone);
            userID = findViewById(R.id.Userid);
            password = findViewById(R.id.Pass);
            repassword = findViewById(R.id.Repass);
            Exit = findViewById(R.id.exit);
            Go = findViewById(R.id.Go);
            haveaccount = findViewById(R.id.Already);
            login = findViewById(R.id.Login);
            checkbox1 = findViewById(R.id.rememberuser);
            checkbox2 = findViewById(R.id.rememberpass);
            row1 = findViewById(R.id.Namerow);
            row2 = findViewById(R.id.Phonerow);
            row3 = findViewById(R.id.Useridrow);
            row4 = findViewById(R.id.Passrow);
            row5 = findViewById(R.id.Repassrow);

            Go.setOnClickListener(view -> go(view));
            Exit.setOnClickListener(view -> funcCancel(view));

        }


    }

    private void HideRow(View view){
        row1.setVisibility(view.GONE);
        row2.setVisibility(view.GONE);
        row5.setVisibility(view.GONE);


    }
    public void go1(View view){
        userID = findViewById(R.id.Userid);
        String id=userID.getText().toString().trim();
        String pass=s4;
        if(id.equals(s3) && s4.equals(pass)){
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);

        }
        else
            Toast.makeText(getApplicationContext(),"Please enter the correct  Id and password",Toast.LENGTH_SHORT).show();


    }
    public void go(View view){

       /* if(Integer.parseInt(String.valueOf(userID))>0 && password==repassword && phone.length()==11 && s1=="NO" && s2=="NO"){
            String mail1= mail.getText().toString().trim();
            for(int i=0;i<mail1.length();i++) {
                if(mail1.charAt(i)=='@') {*/

                    SharedPreferences sp=this.getSharedPreferences("user_details",MODE_PRIVATE);
                    SharedPreferences .Editor e=sp.edit();
                    e.putString("user-id",userID.getText().toString().trim());
                    e.putString("User-name",name.getText().toString().trim());
                    e.putString("password",password.getText().toString().trim());
                    e.putString("Mail",mail.getText().toString().trim());
                    e.putString("Phone",name.getText().toString().trim());
                    if(checkbox1.isChecked() ){
                        e.putString("REM_USER","YES");

                    }
                    else{
                        e.putString("REM_USER","NO");

                    }
                    if(checkbox2.isChecked() ){

                        e.putString("REM_PASS","YES");
                    }
                    else{
                        e.putString("REM_PASS","NO");
                    }
                    e.apply();
                    Toast.makeText(getApplicationContext(),"Data stored",Toast.LENGTH_SHORT).show();

               /* }
            }

        }*/




    }


    public void funcCancel(View view){

        finish();
        System.exit(0);
    }



}
