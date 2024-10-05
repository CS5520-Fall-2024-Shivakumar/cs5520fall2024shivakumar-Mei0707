package com.example.numad24fa_qiaowenmei;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class QuickCalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//         get data passed from main activity
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.






//         Set the layout as the content view
        setContentView(R.layout.quick_cal_layout);
    }
}
