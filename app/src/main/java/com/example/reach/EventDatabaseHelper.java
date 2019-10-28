package com.example.reach;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class EventDatabaseHelper extends SQLiteOpenHelper{
    static String DATABASE_NAME = "";
    static int VERSION_NUM = 1;

    public static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " +  EventDatabaseContract.EventEntry.tblUser_name + " (" + EventDatabaseContract.EventEntry.user_id + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EventDatabaseContract.EventEntry.email + " TEXT," +
                    EventDatabaseContract.EventEntry.usrnme + " TEXT," +
                    EventDatabaseContract.EventEntry.pwd + " TEXT)";


    public static final String SQL_CREATE_ENTRIES__EVENTS =
            "CREATE TABLE " + EventDatabaseContract.EventEntry.tblEvent_name + " (" + EventDatabaseContract.EventEntry.event_id + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EventDatabaseContract.EventEntry.event_id + " INTEGER," +
                    EventDatabaseContract.EventEntry.user_id + " INTEGER," +
                    EventDatabaseContract.EventEntry.event_name + " TEXT," +
                    EventDatabaseContract.EventEntry.location + " TEXT," +
                    EventDatabaseContract.EventEntry.range + " INTEGER," +
                    EventDatabaseContract.EventEntry.event_type_id + " INTEGER," +
                    EventDatabaseContract.EventEntry.capacity + " INTEGER," +
                    EventDatabaseContract.EventEntry.start_date + " DATE," +
                    EventDatabaseContract.EventEntry.end_date + " DATE," +
                    EventDatabaseContract.EventEntry.desc + " TEXT)";

    public static final String SQL_CREATE_ENTRIES__EVENT_TYPE =
            "CREATE TABLE " + EventDatabaseContract.EventEntry.tblEventTypes_name + " (" + EventDatabaseContract.EventEntry.event_type_id + "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EventDatabaseContract.EventEntry.event_type + " INTEGER)";

    public EventDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(dropTable(EventDatabaseContract.EventEntry.tblEvent_name));
        db.execSQL(dropTable(EventDatabaseContract.EventEntry.tblUser_name));
        db.execSQL(dropTable(EventDatabaseContract.EventEntry.tblEventTypes_name));
        onCreate(db);

    }
    public String dropTable(String name)
    {
        String query = "DROP TABLE IF EXISTS" + name;
        return query;
    }

    public boolean addUser(String email, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventDatabaseContract.EventEntry.email, email);
        contentValues.put(EventDatabaseContract.EventEntry.usrnme, username);
        contentValues.put(EventDatabaseContract.EventEntry.pwd, password);

        Log.d("WEW LAD", "addUser: Adding " + email + "/" + username + "/" + password + " to " + EventDatabaseContract.EventEntry.tblUser_name);

        long newRowId = db.insert(EventDatabaseContract.EventEntry.tblUser_name, null, contentValues);

    return true;
    }
    public boolean addEvent(int user_id, String event_name, String location, int range, int event_type_id, int capacity, String start_date, String end_date, String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventDatabaseContract.EventEntry.user_id, user_id);
        contentValues.put(EventDatabaseContract.EventEntry.event_name, event_name);
        contentValues.put(EventDatabaseContract.EventEntry.location, location);
        contentValues.put(EventDatabaseContract.EventEntry.range, range);
        contentValues.put(EventDatabaseContract.EventEntry.event_type_id, event_type_id);
        contentValues.put(EventDatabaseContract.EventEntry.capacity, capacity);
        contentValues.put(EventDatabaseContract.EventEntry.start_date, start_date);
        contentValues.put(EventDatabaseContract.EventEntry.end_date, end_date);
        contentValues.put(EventDatabaseContract.EventEntry.desc, desc);

        Log.d("WEW LAD", "addEvent: Adding " + event_name + " to " + EventDatabaseContract.EventEntry.tblEvent_name);

        long newRowId = db.insert(EventDatabaseContract.EventEntry.tblEvent_name, null, contentValues);
        return true;
    }
    public boolean addEventType(int event_type_id, int event_type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventDatabaseContract.EventEntry.event_type_id, event_type_id);
        contentValues.put(EventDatabaseContract.EventEntry.event_type, event_type);

        Log.d("WEW LAD", "addEventType: Adding " + event_type_id + "/" + event_type +  " to " + EventDatabaseContract.EventEntry.tblEvent_name);


        long newRowId = db.insert(EventDatabaseContract.EventEntry.tblEventTypes_name, null, contentValues);
        return true;
    }
}
