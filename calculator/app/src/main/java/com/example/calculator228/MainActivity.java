package com.example.calculator228;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText textfield;
    String s1;
    // String resultdet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> arrPackage;
        final ArrayList<String> arrPackage1;
        final EditText history = findViewById(R.id.textfield);


        Button save = findViewById(R.id.result);
        Button read = findViewById(R.id.history);
        Button read1 = findViewById(R.id.history1);
        Button delete = findViewById(R.id.delete);

        arrPackage = new ArrayList<>();
        arrPackage1 = new ArrayList<>();
        final SharedPreferences sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
        final SharedPreferences sharedPreferences1 = getSharedPreferences("USER1",MODE_PRIVATE);
        /*SharedPreferences sp=this.getSharedPreferences("USER",MODE_PRIVATE);
        sp.edit().clear().commit();
        SharedPreferences sp1=this.getSharedPreferences("USER1",MODE_PRIVATE);
        sp1.edit().clear().commit();*/
       // sp.edit().remove("resultdet").commit();
        read.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = sharedPreferences.getString("Set", "");
                if (json.isEmpty()) {
                    Toast.makeText(MainActivity.this,"There is something error",Toast.LENGTH_LONG).show();
                } else {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> arrPackageData = gson.fromJson(json, type);
                    System.out.println("JSN LENGTH"+arrPackage.size());
                    String[] str= new String[arrPackageData.size()];
                    int count=0;
                    for(String data:arrPackageData) {
                       //
                        str[count]=data;
                        System.out.println(str[count]);
                        count++;
                    }
                    String str1 = Arrays.toString(str);
                    System.out.println(str1);
                    history.setText(str1);
                    Toast.makeText(MainActivity.this,"Current history",Toast.LENGTH_LONG).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {

                history.setText("");

            }
        });


        read1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = sharedPreferences1.getString("Set", "");
                if (json.isEmpty()) {
                    Toast.makeText(MainActivity.this,"There is something error",Toast.LENGTH_LONG).show();
                } else {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> arrPackageData1 = gson.fromJson(json, type);
                    System.out.println("JSN LENGTH"+arrPackageData1.size());
                    String[] str= new String[arrPackageData1.size()];
                    int count=0;
                    for(String data:arrPackageData1) {
                        //
                        str[count]=data;
                        System.out.println(str[count]);
                        count++;
                    }
                    String str1 = Arrays.toString(str);
                    System.out.println(str1);
                    history.setText(str1);
                    Toast.makeText(MainActivity.this,"Previous history",Toast.LENGTH_LONG).show();
                }
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(history.getText().toString().isEmpty() ) {
                    Toast.makeText(MainActivity.this,"Plz Enter all the data",Toast.LENGTH_LONG).show();
                }else{
                    String nameData = history.getText().toString().trim();

                    arrPackage.add(nameData);
                    System.out.println(arrPackage.size());
                    Gson gson = new Gson();
                    String json = gson.toJson(arrPackage);


                    Gson gson1 = new Gson();
                    String json1 = sharedPreferences.getString("Set", "");
                    if (json1.isEmpty()) {
                        //Toast.makeText(MainActivity.this,"There is something error",Toast.LENGTH_LONG).show();
                    } else {
                        Type type1 = new TypeToken<List<String>>() {
                        }.getType();


                        List<String> arrPackageData1 = gson.fromJson(json1, type1);
                        arrPackageData1.add(nameData);
                        String json2 = gson1.toJson(arrPackageData1);
                        //System.out.println("JSN LENGTH"+arrPackage.size());
                       // String[] str1= new String[arrPackageData1.size()];

                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("Set",json2 );
                        editor1.commit();
                    }


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Set",json );
                    editor.commit();
                }


            }
        });


    }

}