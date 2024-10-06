package com.example.numad24fa_qiaowenmei;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlinx.coroutines.FlowPreview;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout to hold the views
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 30, 30, 30);
        layout.setGravity(android.view.Gravity.CENTER);

        // Create a TextView for "Hello World!"
        TextView helloText = new TextView(this);
        helloText.setText("Hello World!");
        helloText.setTextSize(24);
        helloText.setGravity(android.view.Gravity.CENTER);

        // Create a Button for "About Me"
        Button aboutMeButton = new Button(this);
        aboutMeButton.setText("About Me");

        // Set onClickListener for the Button to show a Toast with your info
        aboutMeButton.setOnClickListener(v -> Toast.makeText(MainActivity.this,
                "Qiaowen Mei\nmqiaowen@gmail.com", Toast.LENGTH_LONG).show());

        Button quickCalButton = new Button(this);
        quickCalButton.setText("Quick Calculator");

        quickCalButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, QuickCalActivity.class);
            myIntent.putExtra("key", "test_value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });

        // Add TextView and Button to the layout
        layout.addView(helloText);
        layout.addView(aboutMeButton);
        layout.addView(quickCalButton);

        // Set the layout as the content view
        setContentView(layout);
    }
}