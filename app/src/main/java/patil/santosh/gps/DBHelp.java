package patil.santosh.gps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MsP on 29-01-2016.
 */
public class DBHelp extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "GPSData4";
    private static final int DATABASE_VERSION = 1;
    public static final String PERSON_TABLE_NAME = "person";
    public static final String PERSON_COLUMN_ID = "_id";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_MOBILE = "mobile";
    public static final String PERSON_COLUMN_LAT = "lat";
    public static final String PERSON_COLUMN_LNG = "lng";

    public DBHelp(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        db.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + "(" +
                        PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        PERSON_COLUMN_NAME + " DATETIME, " +
                        PERSON_COLUMN_MOBILE + " TEXT, " +
                        PERSON_COLUMN_LAT + " REAL, " +
                        PERSON_COLUMN_LNG + " REAL)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(db);

    }

    public boolean insertPerson(String mobile, double lat,double lng) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, dateFormat.format(date));
        contentValues.put(PERSON_COLUMN_MOBILE, mobile);
        contentValues.put(PERSON_COLUMN_LAT, lat);
        contentValues.put(PERSON_COLUMN_LNG, lng);
        db.insert(PERSON_TABLE_NAME, null, contentValues);

        db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + PERSON_TABLE_NAME, null );

        Log.d("",res.getCount() + "/////////////////////////////////////");

        return true;
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + PERSON_TABLE_NAME, null );
        return res;
}



}
