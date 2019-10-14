package com.example.lab4v01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lunchlist.db";
    private static final String TABLE_RESTAURANTS = "restaurants";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String TYPE = "type";

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        Log.d("DatabaseHelper", "DatabaseHelper: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create table user

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RESTAURANTS);
        String createTableRestaurants = "CREATE TABLE "+TABLE_RESTAURANTS+" ("+
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME +" TEXT, "+
                ADDRESS+" TEXT, "+
                TYPE+" TEXT)";
        db.execSQL(createTableRestaurants);
    }
    public boolean insert(String name,String address,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(ADDRESS,address);
        contentValues.put(TYPE,type);
        long ins = db.insert(TABLE_RESTAURANTS,null,contentValues);
        db.close();
        if(ins == -1) return false;
        else return true;
    }
    public ArrayList<Restaurant> getAllRestaurants(){
        ArrayList<Restaurant> list = new ArrayList<>();
        //select all query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANTS,
                new String[]{ID,NAME,ADDRESS,TYPE},null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setName(cursor.getString(1));
                restaurant.setAddress(cursor.getString(2));
                restaurant.setType(cursor.getString(3));
                list.add(restaurant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public int countRestaurants(){
        int values = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANTS,new String[]{ID,NAME,ADDRESS,TYPE},null,null,null,null,null);
        if(cursor != null){
            values = cursor.getCount();
        }
        cursor.close();
        db.close();
        return values;
    }

}
