package com.example.numad24fa_qiaowenmei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Class for database helper
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table in database
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if exists and create table again
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    // Insert Function to insert data in database
    public long insertContact(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_NAME, name);
        contentValues.put(Constants.C_PHONE, phone);

        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    // Update Function to update data in database
    public void updateContact(String id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_NAME, name);
        contentValues.put(Constants.C_PHONE, phone);

        db.update(Constants.TABLE_NAME, contentValues, Constants.C_ID + " =? ", new String[]{id});
        db.close();
    }

    // Delete Function to delete data by id
    public void deleteContact(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " =? ", new String[]{id});
        db.close();
    }

    // Fetch all contacts (if needed)
    public ArrayList<ModelContact> getAllData() {
        ArrayList<ModelContact> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                ModelContact modelContact = new ModelContact(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE))
                );
                arrayList.add(modelContact);
            } while (cursor.moveToNext());
        }

        cursor.close(); // Close the cursor to prevent memory leaks
        db.close(); // Close the database
        return arrayList; // Return the list of contacts
    }
}
