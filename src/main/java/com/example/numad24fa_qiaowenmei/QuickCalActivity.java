package com.example.numad24fa_qiaowenmei;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.numad24fa_qiaowenmei.databinding.QuickCalLayoutBinding;


public class QuickCalActivity extends AppCompatActivity {

    private QuickCalViewModel viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the layout as the content view
        setContentView(R.layout.quick_cal_layout);


        QuickCalLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.quick_cal_layout);
        viewModel = new ViewModelProvider(this).get(QuickCalViewModel.class);
        // Bind the ViewModel to the layout
        binding.setViewModel(viewModel);

        binding.setLifecycleOwner(this);




        //get data passed from main activity
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.


        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> appendText(viewModel, "1"));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> appendText(viewModel, "2"));

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> appendText(viewModel, "3"));

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> appendText(viewModel, "4"));

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> appendText(viewModel, "5"));

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> appendText(viewModel, "6"));

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> appendText(viewModel, "7"));

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> appendText(viewModel, "8"));

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(v -> appendText(viewModel, "9"));

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(v -> appendText(viewModel, " + "));

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(v -> appendText(viewModel, "0"));

        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v -> appendText(viewModel, " - "));

        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(v -> deleteLastCharacter(viewModel));

        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(v -> eval(viewModel));

    }

    void appendText(QuickCalViewModel viewModel, String str) {
        String text = viewModel.getText().get();
        if ("CALC".equals(text)) {
            text = "";
        }
        viewModel.setText(text + str);
    }

    void eval(QuickCalViewModel viewModel) {

    }

    void deleteLastCharacter(QuickCalViewModel viewModel) {
        String text = viewModel.getText().get();
        assert text != null;
        viewModel.setText(text.substring(0, text.length() - 1));
    }
}
