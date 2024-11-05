package com.example.numad24fa_qiaowenmei;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button aboutMe;
    Button quickCal;
    Button contactsCollector;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        aboutMe = findViewById(R.id.aboutMe);
        quickCal = findViewById(R.id.quickCal);
        contactsCollector = findViewById(R.id.contactsCollector);

        // Create a TextView for "Hello World!"
        TextView helloText = findViewById(R.id.helloText);
        helloText.setText("Hello World!");


        // Set onClickListener for the Button
        aboutMe.setOnClickListener(v -> Toast.makeText(MainActivity.this,
                "Qiaowen Mei\nmqiaowen@gmail.com", Toast.LENGTH_LONG).show());


        quickCal.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, QuickCalActivity.class);
            myIntent.putExtra("key", "test_value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Alert","Lets See if it Works !!!");
            }
        });

        contactsCollector.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ContactActivity.class);
            try {
                MainActivity.this.startActivity(myIntent);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


    }
}