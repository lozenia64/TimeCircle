package com.ljy93.timecircle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler3 extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TCMDB";
    private static final String TABLE_MONTH = "monthTable";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NAME = "name";
    private static final String KEY_STIME = "startTime";
    private static final String KEY_ETIME = "endTime";
    private static final String KEY_COLOR = "color";

    public DbHandler3(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MONTH + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_STIME + " TEXT,"
                + KEY_ETIME + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_COLOR + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTH);
        onCreate(db);
    }
    void insertUserDetails(String date, String startTime, String endTime, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_DATE, date);
        cValues.put(KEY_STIME, startTime);
        cValues.put(KEY_ETIME, endTime);
        cValues.put(KEY_NAME, name);
        long newRowId = db.insert(TABLE_MONTH,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, date, startTime, endTime, name, color FROM "+ TABLE_MONTH+" ORDER BY date, startTime, endTime";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("date",cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("color",cursor.getString(cursor.getColumnIndex(KEY_COLOR)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MONTH, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }}