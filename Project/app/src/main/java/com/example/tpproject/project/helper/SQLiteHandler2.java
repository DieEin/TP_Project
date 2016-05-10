package com.example.tpproject.project.helper;

/**
 * Created by Tomi on 7.5.2016 г..
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler2 extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "androids_api";

    // Login table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_PLAYERSTATS = "player_stats";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    private static final String KEY_MONEY = "money";
    private static final String KEY_XP = "xp";
    private static final String KEY_LEVEL = "level";

    public SQLiteHandler2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "in onCreate");

        String CREATE_PLAYERSTATS_TABLE = "CREATE TABLE " + TABLE_PLAYERSTATS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_MONEY + " TEXT," + KEY_XP + " TEXT,"
                + KEY_LEVEL + " TEXT" + ");";
        db.execSQL(CREATE_PLAYERSTATS_TABLE);

        //db.execSQL("CREATE TABLE player_stats( \"id INTEGER PRIMARY KEY, name TEXT, money TEXT, xp TEXT, level TEXT\" );");

        Log.d(TAG, "Database tables2 created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERSTATS);

        // Create tables again
        onCreate(db);
    }

    public void addStats(String name, String money, String xp, String level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_MONEY, money); // Money
        values.put(KEY_XP, xp); // Xp
        values.put(KEY_LEVEL, level); // Level

        // Inserting Row
        long id = db.insert(TABLE_PLAYERSTATS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New stats inserted into sqlite: " + id);
    }

    public HashMap<String, String> getPlayerStats() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_PLAYERSTATS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("money", cursor.getString(2));
            user.put("xp", cursor.getString(3));
            user.put("level", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;

        /*HashMap<String, String> player_stats = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERSTATS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            player_stats.put("name", cursor.getString(1));
            player_stats.put("money", cursor.getString(2));
            player_stats.put("xp", cursor.getString(3));
            player_stats.put("level", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching player_stats from Sqlite: " + player_stats.toString());

        return player_stats;*/
    }

    public void deletePlayerStats() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        Log.d(TAG, "Still have not deleted all player_stats info from sqlite");
        db.delete(TABLE_PLAYERSTATS, null, null);
        db.close();

        Log.d(TAG, "Deleted all player_stats info from sqlite");
    }

}