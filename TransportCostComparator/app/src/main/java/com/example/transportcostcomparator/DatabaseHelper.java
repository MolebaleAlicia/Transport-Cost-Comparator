package com.example.transportcostcomparator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transport_cost_comparator.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "transport_reports";
    private static final String COL_ID = "id";
    private static final String COL_MODE = "mode_of_transport";
    private static final String COL_TYPE = "transport_type";
    private static final String COL_DAILY_COST = "daily_cost";
    private static final String COL_MONTHLY_COST = "monthly_cost";
    private static final String COL_MONTHLY_DISTANCE = "monthly_distance";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MODE + " TEXT, " +
                COL_TYPE + " TEXT, " +
                COL_DAILY_COST + " REAL, " +
                COL_MONTHLY_COST + " REAL, " +
                COL_MONTHLY_DISTANCE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertReport(String modeOfTransport, String transportType,
                                double dailyCost, double monthlyCost, double monthlyDistance) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_MODE, modeOfTransport);
        values.put(COL_TYPE, transportType);
        values.put(COL_DAILY_COST, dailyCost);
        values.put(COL_MONTHLY_COST, monthlyCost);
        values.put(COL_MONTHLY_DISTANCE, monthlyDistance);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }

    public Cursor getLatestReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC LIMIT 1";
        return db.rawQuery(query, null);
    }
}