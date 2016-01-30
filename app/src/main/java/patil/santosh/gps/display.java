package patil.santosh.gps;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by MsP on 29-01-2016.
 */
public class display extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        Log.d("", "11**************************");
        DBHelp DBHelper = new DBHelp(this);
        Log.d("","21**************************");
        final Cursor cursor = DBHelper.getAll();
        Log.d("","31**************************");
        String [] columns = new String[] {
                DBHelper.PERSON_COLUMN_ID,
                DBHelper.PERSON_COLUMN_NAME,
                DBHelper.PERSON_COLUMN_MOBILE,
                DBHelper.PERSON_COLUMN_LAT,
                DBHelper.PERSON_COLUMN_LNG
        };
        Log.d("","41**************************");
        int [] widgets = new int[] {
                R.id.personID,
                R.id.personName,
                R.id.personMobile,
                R.id.personLat,
                R.id.personLng
        };
        Log.d("","51**************************");

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.format, cursor, columns, widgets, 0);
        Log.d("","1**************************");
        GridView gridView = (GridView) findViewById(R.id.gridView2);
        //ListView listView = (ListView)findViewById(R.id.listView);
        Log.d("", "2******************************");
        gridView.setAdapter(cursorAdapter);
       // listView.setAdapter(cursorAdapter);
    }
}