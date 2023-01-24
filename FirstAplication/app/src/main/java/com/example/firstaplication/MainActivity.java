package com.example.firstaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    EditText name, place, datetime, capacity, budget, email, phone, Description;
    Button save;
    Button cancel;
    RadioButton indoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondlab_work);
        /*name = findViewById(R.id.fst);
        place = findViewById(R.id.second);
        datetime = findViewById(R.id.six);
        capacity = findViewById(R.id.seven);
        budget = findViewById(R.id.eight);
        email = findViewById(R.id.nine);
        phone = findViewById(R.id.ten);
        Description = findViewById(R.id.eleven);
        indoor = findViewById(R.id.third);
        save = findViewById(R.id.fourteen);
        cancel = findViewById(R.id.twelve);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> funcCancel());*/
    }
}
   /* public void funcSave(){
        System.out.println(name.getText().toString().trim());
        System.out.println(place.getText().toString().trim());
        System.out.println(datetime.getText().toString().trim());
        System.out.println(capacity.getText().toString().trim());
        System.out.println(budget.getText().toString().trim());
        System.out.println(email.getText().toString().trim());
        System.out.println(phone.getText().toString().trim());
        System.out.println(Description.getText().toString().trim());
        if(indoor.isChecked()){
            System.out.println("Indoor Clicked");
        }

    }
    public void funcCancel(){

        finish();
    }
}*/