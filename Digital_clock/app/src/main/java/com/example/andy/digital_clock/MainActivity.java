package com.example.andy.digital_clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Handler mHandler;

    int hour,second,minute,count=0;
    TextView S,M,H;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        S=findViewById(R.id.second);
        M=findViewById(R.id.min);
        H=findViewById(R.id.hour);
        exit=findViewById(R.id.exit);

        Calendar c= Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);
        second=c.get(Calendar.SECOND);
        minute=c.get(Calendar.MINUTE);

        exit.setOnClickListener(view -> funcCancel());

        clock();

    }

    public void clock(){


        Thread t=new Thread(){


            @Override
            public void run(){

                while(!isInterrupted()){

                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {


                                if(hour>12){
                                    hour=hour-12;
                                }

                                if(hour==0){
                                    hour=12;
                                }


                                second++;

                                if(second==60){
                                    second=0;
                                    minute++;
                                }
                                if(minute==60){
                                    minute=0;

                                    hour++;
                                    if(hour>12){
                                        hour=hour-12;
                                    }
                                }
                                String s=String.valueOf(second);
                                String m=String.valueOf(minute);
                                String h=Integer.toString(hour);
                                if(s.length()==1){
                                    String s1=0+s;
                                    S.setText(":"+s1);
                                }
                                else
                                    S.setText(":"+String.valueOf(second));

                                if(m.length()==1){
                                    String s1=0+m;
                                    M.setText(":"+s1);
                                }
                                else
                                    M.setText(":"+String.valueOf(minute));

                                if(h.length()==1){
                                    String s2=0+h;
                                    H.setText(s2);
                                }
                                else
                                    H.setText(String.valueOf(hour));

                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();

    }


    public void funcCancel(){

        finish();
    }

}

