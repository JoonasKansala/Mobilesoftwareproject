/**
 * Created by Joonas on 17.11.2015.
 */

package joonas.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

    //Initialising all the necessary components for database adapter class
    private SQLiteDatabase db;
    private DatabaseHelper myDBHelper;
    private final Context context;

    //Database name, table name, columns, version
    public static final String DATABASE_NAME = "RateYourMusicDatabase.db";
    public static final String TABLE_NAME = "Music_rating_table";
    public static final int DATABASE_VERSION = 1;
    public static String COL_1 = "_id";
    public static final String COL_2 = "ARTIST";
    public static final String COL_3 = "ALBUM";
    public static final String COL_4 = "TRACK";
    public static final String COL_5 = "YEAR";
    public static final String COL_6 = "RATING";

    //This nested class handles all of the table creations and upgrades
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ARTIST TEXT," +
                    "ALBUM TEXT," +
                    "TRACK TEXT," +
                    "YEAR INTEGER," +
                    "RATING FLOAT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    //Basic constructor for this class
    public DatabaseAdapter(Context ctx)
    {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    //Gets the writable database;
    public DatabaseAdapter open()
    {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    //Insert a row into the database. If row is inserted, returns true, otherwise false
    public boolean insertMusicData(String artist, String album, String track, String year, float rating)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, artist);
        contentValues.put(COL_3, album);
        contentValues.put(COL_4, track);
        contentValues.put(COL_5, year);
        contentValues.put(COL_6, rating);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Selects all of the rows in the database
    public Cursor getAllMusicData()
    {
        String where = null;

        String[] musicData = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6};

        //Executes SQL query
        Cursor musicCursor = db.query(true, TABLE_NAME, musicData, where, null, null, null, null, null);
        if(musicCursor != null)
        {
            musicCursor.moveToFirst(); //Starts from the first row in the database;
        }
        return musicCursor;
    }

    //Updates a row in the database
    public boolean updateMusicData(String id, String artist, String album, String track, String year, float rating)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, artist);
        contentValues.put(COL_3, album);
        contentValues.put(COL_4, track);
        contentValues.put(COL_5, year);
        contentValues.put(COL_6, rating);
        db.update(TABLE_NAME, contentValues, " _id = ? ", new String[] {id});
        return true;
    }

    //Deletes a row in the database
    public Integer deleteMusicRow(String rowID)
    {
        return db.delete(TABLE_NAME, " _id = ? ", new String[]{rowID});
    }
}

