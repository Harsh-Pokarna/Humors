package com.example.humors.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HumorsDB";
    private static final String TABLE_NAME = "StepCounter";
    private static final String ID_COL = "step_id";
    private static final String DATE_COL = "date";
    private static final String STEP_COL = "step_count";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT,"
                + STEP_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);

    }

    public void addSteps(String date, float steps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_COL, date);
        contentValues.put(STEP_COL, Float.toString(steps));

        db.insert(TABLE_NAME, null, contentValues);

        Log.e("TAG", "added steps entry is: " + date + " " + steps);

        db.close();
    }

    public ArrayList<String> getSteps() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        ArrayList<String> id = new ArrayList<>();
//        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> steps = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
//                id.add(cursor.getString(0));
//                date.add(cursor.getString(1));
                steps.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return steps;
    }

    public ArrayList<String> getIds() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<String> id = new ArrayList<>();
//        ArrayList<String> date = new ArrayList<>();
//        ArrayList<String> steps = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getString(0));
//                date.add(cursor.getString(1));
//                steps.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return id;
    }

    public ArrayList<String> getDate() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
//        ArrayList<String> steps = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
//                id.add(cursor.getString(0));
                date.add(cursor.getString(1));
//                steps.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return date;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
