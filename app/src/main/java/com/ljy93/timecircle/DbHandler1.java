package com.ljy93.timecircle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler1 extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TCDDB";
    private static final String TABLE_DAY = "todayTable";
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_NAME = "name";
    private static final String KEY_STIME = "startTime";
    private static final String KEY_ETIME = "endTime";

    public DbHandler1(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_DAY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DAY + " TEXT,"
                + KEY_STIME + " TEXT,"
                + KEY_ETIME + " TEXT,"
                + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAY);
        onCreate(db);
    }
    // 추가
    void insertUserDetails(String day, String startTime, String endTime, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_DAY, day);
        cValues.put(KEY_STIME, startTime);
        cValues.put(KEY_ETIME, endTime);
        cValues.put(KEY_NAME, name);
        long newRowId = db.insert(TABLE_DAY,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, day, startTime, endTime, name FROM "+ TABLE_DAY;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("day",cursor.getString(cursor.getColumnIndex(KEY_DAY)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            userList.add(user);
        }
        return  userList;
    }
    public ArrayList<HashMap<String, String>> GetUsersid(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id FROM "+ TABLE_DAY+" ORDER BY id DESC";
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
        String query = "SELECT day, startTime, endTime, name FROM "+ TABLE_DAY;
        Cursor cursor = db.query(TABLE_DAY, new String[]{KEY_DAY, KEY_STIME, KEY_ETIME, KEY_NAME}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("day",cursor.getString(cursor.getColumnIndex(KEY_DAY)));
            user.put("startTime",cursor.getString(cursor.getColumnIndex(KEY_STIME)));
            user.put("endTime",cursor.getString(cursor.getColumnIndex(KEY_ETIME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            userList.add(user);
        }
        return userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DAY, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String day, String startTime, String endTime, String name, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_DAY, day);
        cVals.put(KEY_STIME, startTime);
        cVals.put(KEY_ETIME, endTime);
        cVals.put(KEY_NAME, name);
        int count = db.update(TABLE_DAY, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}