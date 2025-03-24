package com.jivan.myapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    // Database name
    private static final String DB_NAME = "courses";
    private static final int DB_VERSION = 1;
    // Name of our table
    private static final String TABLE_NAME = "mycourses";
    // Columns for the table
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DURATION_COL = "duration";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( " + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_COL + " TEXT, " + DURATION_COL + " TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewCourse(String name, String duration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(DURATION_COL, duration);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public Cursor getAlCourses() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * from " + TABLE_NAME, null);
    }
}
