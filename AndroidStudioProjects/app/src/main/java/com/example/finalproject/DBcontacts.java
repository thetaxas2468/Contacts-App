package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBcontacts extends SQLiteOpenHelper {

    private static final String DB_NAME = "Contacts";
    private static final int DB_VERSION = 1;

    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE = "Phone";
    private static final String KEY_LOCATION = "Location";
    private static final String KEY_POSTCODE = "Postcode";
    private static final String TABLE_NAME = "Information";
    private static final String CREATE_TABLE ="CREATE TABLE " + TABLE_NAME + "(" + KEY_NAME + " VARCHAR(255), "
            + KEY_PHONE + " VARCHAR(255) ,"+
            KEY_LOCATION + " VARCHAR(255) ,"+
            KEY_POSTCODE + " INTEGER);";
    private Context context;
    private static final String DROP_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBcontacts(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Comment.message(context,""+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Comment.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Comment.message(context,""+e);
        }

    }


    public long addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_LOCATION, contact.getLocation());
        values.put(KEY_POSTCODE, contact.getPostcode());

        long id=db.insert(TABLE_NAME, null, values);
        return id;
    }

    public String getAllContacts() {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {KEY_NAME,KEY_PHONE,KEY_LOCATION,KEY_POSTCODE};
        Cursor cursor =db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            @SuppressLint("Range")int postcode = cursor.getInt(cursor.getColumnIndex(KEY_POSTCODE));
            @SuppressLint("Range")String name =cursor.getString(cursor.getColumnIndex(KEY_NAME));
            @SuppressLint("Range")String location =cursor.getString(cursor.getColumnIndex(KEY_LOCATION));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            buffer.append("Name: "+name+ "\n" + "Location: "+location + "\n" + "PN: "+phone +"\n"+ "Postcode: "+postcode + "\n\n\n\n");
        }
        cursor.close();
        return buffer.toString();
    }




    public int updateContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_LOCATION, contact.getLocation());
        values.put(KEY_POSTCODE, contact.getPostcode());

        int count=db.update(TABLE_NAME, values, "postcode=?", new String[]{String.valueOf(contact.getPostcode())});
        return count;
    }

    public int deleteContact(int postcode) {
        SQLiteDatabase db = this.getWritableDatabase();

        int count=db.delete(TABLE_NAME, "postcode=?", new String[]{String.valueOf(postcode)});
        return count;
    }

    public String searchContacts(String tempname) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {KEY_NAME,KEY_PHONE,KEY_LOCATION,KEY_POSTCODE};
        Cursor cursor =db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            @SuppressLint("Range")int postcode = cursor.getInt(cursor.getColumnIndex(KEY_POSTCODE));
            @SuppressLint("Range")String name =cursor.getString(cursor.getColumnIndex(KEY_NAME));
            @SuppressLint("Range")String location =cursor.getString(cursor.getColumnIndex(KEY_LOCATION));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            if(tempname.equals(name)) {
                buffer.append("Name: " + name + "\n" + "Location: " + location + "\n" + "PN: " + phone + "\n" + "Postcode: " + postcode + "\n\n\n\n");
            }
        }
        cursor.close();
        return buffer.toString();
    }
}
