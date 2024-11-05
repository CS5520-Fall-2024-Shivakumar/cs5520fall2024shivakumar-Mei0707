package com.example.numad24fa_qiaowenmei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    // View elements
    private FloatingActionButton fab;
    private RecyclerView contactRv;

    // Database helper
    private DatabaseHelper dbHelper;

    // Adapter for contacts
    private AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        dbHelper = new DatabaseHelper(this);

        fab = findViewById(R.id.fab);
        contactRv = findViewById(R.id.contactRv);
        contactRv.setLayoutManager(new LinearLayoutManager(this));

        contactRv.setHasFixedSize(true);

        // Set up FloatingActionButton click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddEditContact activity in add mode
                Intent intent = new Intent(ContactActivity.this, AddEditContact.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);
            }
        });

        loadData();
    }

    // Load contacts from database
    private void loadData() {
        ArrayList<ModelContact> contactList = dbHelper.getAllData();
        Log.d("ContactList", "Loaded contacts: " + contactList.size());
        for (ModelContact contact : contactList) {
            Log.d("ContactList", "Contact: " + contact.getName() + ", " + contact.getPhone());
        }
        adapterContact = new AdapterContact(this, dbHelper.getAllData());
        contactRv.setAdapter(adapterContact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Refresh data when returning to this activity
    }

}
