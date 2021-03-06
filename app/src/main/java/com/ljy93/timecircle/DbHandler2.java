package com.ljy93.timecircle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler2 extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TCWDB";
    private static final String TABLE_WEEK = "weekTable";
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_NAME = "name";
    private static final String KEY_STIME = "startTime";
    private static final String KEY_ETIME = "endTime";
    private static final String KEY_COLOR = "color";

    public DbHandler2(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WEEK + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DAY + " TEXT,"
                + KEY_STIME + " TEXT,"
                + KEY_ETIME + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_COLOR + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEK);
        onCreate(db);
    }
    // 추가
    void insertUserDetails(String day, String startTime, String endTime, String name, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_DAY, day);
        cValues.put(KEY_STIME, startTime);
        cValues.put(KEY_ETIME, endTime);
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_COLOR, color);
        long newRowId = db.insert(TABLE_WEEK,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, day, startTime, endTime, name, color FROM "+ TABLE_WEEK;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("day",cursor.getString(cursor.getColumnIndex(KEY_DAY)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("color",cursor.getString(cursor.getColumnIndex(KEY_COLOR)));
            userList.add(user);
        }
        return  userList;
    }
    public ArrayList<HashMap<String, String>> GetUsersid(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id FROM "+ TABLE_WEEK+" ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            userList.add(user);
        }
        return  userList;
    }
    // 읽기
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT day, startTime, endTime, name, color FROM "+ TABLE_WEEK;
        Cursor cursor = db.query(TABLE_WEEK, new String[]{KEY_DAY, KEY_STIME, KEY_ETIME, KEY_NAME, KEY_COLOR}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("day",cursor.getString(cursor.getColumnIndex(KEY_DAY)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("color",cursor.getString(cursor.getColumnIndex(KEY_COLOR)));
            userList.add(user);
        }
        return userList;
    }
    public ArrayList<HashMap<String, String>> GetUserByDay(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT day, startTime, endTime FROM "+ TABLE_WEEK;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("day",cursor.getString(cursor.getColumnIndex(KEY_DAY)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            userList.add(user);
        }
        return  userList;
    }    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEEK, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String day, String startTime, String endTime, String name, String color, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_DAY, day);
        cVals.put(KEY_STIME, startTime);
        cVals.put(KEY_ETIME, endTime);
        cVals.put(KEY_NAME, name);
        cVals.put(KEY_COLOR, color);
        int count = db.update(TABLE_WEEK, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}