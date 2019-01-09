package com.example.hello.homeworkdb.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hello.homeworkdb.Util.Constants;
import com.example.hello.homeworkdb.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    public DatabaseHandler( Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create= "CREATE TABLE "+ Constants.Table_Name +"(" + Constants.KEY_ID+" INTEGER PRIMARY KEY,"+Constants.KEY_USER+" TEXT,"+
                Constants.KEY_PASS+" TEXT,"+Constants.KEY_DESC+" TEXT,"+Constants.KEY_IMG+" INT);";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.Table_Name);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_USER,user.getUserName());
        contentValues.put(Constants.KEY_PASS,user.getPassword());
        contentValues.put(Constants.KEY_DESC,user.getDescription());
        contentValues.put(Constants.KEY_IMG,user.getImg());

        db.insert(Constants.Table_Name,null,contentValues);
        db.close();
    }
    public User getUser(String user){
        String count = "SELECT * FROM "+Constants.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(count,null);
        List<String> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                if(user.compareTo((cursor.getString(cursor.getColumnIndex(Constants.KEY_USER))))==0) {
                    User user1 = new User();
                    user1.setUserName(cursor.getString(cursor.getColumnIndex(Constants.KEY_USER)));
                    user1.setDescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESC)));
                    user1.setImg(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMG))));
                    return user1;
                }
            }while(cursor.moveToNext());
        }
        return null;
    }
    public List<User> getAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users= new ArrayList<>();

        try{
            Cursor cursor = db.query(Constants.Table_Name,new String[]
                    {Constants.KEY_ID,Constants.KEY_USER,Constants.KEY_PASS,Constants.KEY_DESC,Constants.KEY_IMG},null,null,null,null,null);

            if(cursor.moveToFirst()){
                do{
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                    user.setUserName(cursor.getString(cursor.getColumnIndex(Constants.KEY_USER)));
                    user.setDescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESC)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(Constants.KEY_PASS)));
                    user.setImg(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMG))));

                    users.add(user);

                }while(cursor.moveToNext());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public List<String> getUserName(){
        String count = "SELECT * FROM "+Constants.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(count,null);
        List<String> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex(Constants.KEY_USER)));
            }while(cursor.moveToNext());
        }
        return list;
    }
    public String getPassword(String Username)
    {
        String count = "SELECT * FROM "+Constants.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(count,null);
        List<String> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                if(Username.compareTo((cursor.getString(cursor.getColumnIndex(Constants.KEY_USER))))==0) {
                    return cursor.getString(cursor.getColumnIndex(Constants.KEY_PASS));
                }
            }while(cursor.moveToNext());
        }
        return null;
    }
    public int getCount(){
        String count = "SELECT * FROM "+Constants.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(count,null);

        return cursor.getCount();
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ Constants.Table_Name);
        db.close();
    }
}
