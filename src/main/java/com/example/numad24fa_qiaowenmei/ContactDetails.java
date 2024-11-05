package com.example.numad24fa_qiaowenmei;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {

    // Views for displaying contact details
    private TextView nameTv, phoneTv;
    private String id;

    // Database helper
    private DatabaseHelper dbHealper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        // Initial
        nameTv = findViewById(R.id.nameTv);
        phoneTv = findViewById(R.id.phoneTv);
        dbHealper = new DatabaseHelper(this);

        // Get data from intent
        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");

        // Load contact data by ID
        if (id != null) {
            loadDataById();
        } else {
            Toast.makeText(this, "Contact ID not found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no ID is provided
        }
    }

    private void loadDataById() {
        // Query to find data by ID
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_ID + " = ?";
        SQLiteDatabase db = dbHealper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});

        if (cursor.moveToFirst()) {
            // Get data
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE));

            // Set data to views
            nameTv.setText(name);
            phoneTv.setText(phone);

        }

        cursor.close();
        db.close();
    }
}

