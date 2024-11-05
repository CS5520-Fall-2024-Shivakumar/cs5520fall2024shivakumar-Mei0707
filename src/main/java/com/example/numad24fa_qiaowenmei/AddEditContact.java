package com.example.numad24fa_qiaowenmei;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class AddEditContact extends AppCompatActivity {

    private EditText editTextName, editTextPhone;
    private Button buttonSave, buttonDelete;

    private String contactId;
    private DatabaseHelper databaseHelper;

//    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);

        databaseHelper = new DatabaseHelper(this);

        // Get the contact ID from the intent if editing an existing contact
        contactId = getIntent().getStringExtra("ID");
        boolean isEditMode = getIntent().getBooleanExtra("isEditMode", false);
        if (isEditMode && contactId != null) {
            loadContactData(contactId);
            buttonDelete.setVisibility(View.VISIBLE); // Show delete button if editing
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactId != null) {
                    // Edit existing contact
                    updateContact(contactId);
                } else {
                    // Add new contact
                    addContact();

                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactId != null) {
                    deleteContact(contactId);
                }
            }
        });
    }

    private void loadContactData(String contactId) {
        String name = getIntent().getStringExtra("NAME");
        String phone = getIntent().getStringExtra("PHONE");
        editTextName.setText(name);
        editTextPhone.setText(phone);
    }

    private void addContact() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (validateInput(name, phone)) {
            databaseHelper.insertContact(name, phone);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonSave), "Contact added successfully!", Snackbar.LENGTH_SHORT);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    finish();
                }
            });
            snackbar.show();
        }

    }

    private void updateContact(String contactId) {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (validateInput(name, phone)) {
            databaseHelper.updateContact(contactId, name, phone);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.editTextName), "Contact updated successfully!", Snackbar.LENGTH_SHORT);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    finish();
                }
            });
            snackbar.show();
        }
    }

    private void deleteContact(String contactId) {
        int rowsDeleted = databaseHelper.getWritableDatabase().delete("contacts", "id=?", new String[]{contactId});
        if (rowsDeleted > 0) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonSave), "Contact deleted successfully!", Snackbar.LENGTH_LONG);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    finish();
                }
            });
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonSave), "Contact deleted successfully!", Snackbar.LENGTH_LONG);
            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    finish();
                }
            });
            snackbar.show();
        }
    }

    private boolean validateInput(String name, String phone) {
        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            return false;
        }
        if (phone.isEmpty()) {
            editTextPhone.setError("Phone number is required");
            return false;
        }
        return true;
    }
}

