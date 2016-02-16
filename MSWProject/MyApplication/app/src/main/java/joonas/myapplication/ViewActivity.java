/**
 * Created by Joonas on 20.11.2015.
 */

package joonas.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewActivity extends AppCompatActivity {

    //Initialise the database adapter and the button widget
    DatabaseAdapter DB;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Used to make activity to view on fullscreen, but doesn't work
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_activity);

        //Created an object of database adapter and got the writable database;
        DB = new DatabaseAdapter(this);
        DB.open();

        //Methods used in this activity
        populateListView();
        escape();
    }

    //http://www.youtube.com/watch?v=c-7sW6UJHw0 <-- reference for the populate list view method

    //Fill the list view with the contents of the database;
    public void populateListView()
    {
        //Executes "select * " query
        Cursor musicCursor = DB.getAllMusicData();

        //String array made out of the table columns
        final String[] musicDataArray = new String[]
                {DatabaseAdapter.COL_1,
                 DatabaseAdapter.COL_2,
                 DatabaseAdapter.COL_3,
                 DatabaseAdapter.COL_4,
                 DatabaseAdapter.COL_5,
                 DatabaseAdapter.COL_6
                };

        //Int array of widgets that are going to get bound with column values
        int[] toViewIDs = new int[]
                {R.id.id_small,
                 R.id.artistDB,
                 R.id.albumDB,
                 R.id.trackDB,
                 R.id.yearDB,
                 R.id.ratingDB};

        //Basically binding the arrays together
        final SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter (
                this,
                R.layout.row_layout,
                musicCursor,
                musicDataArray,
                toViewIDs, 0
                );

        //Initialised and type casted the list view and fill it with the contents of myCursorAdapter
        final ListView listview = (ListView)findViewById(R.id.listView);
        listview.setAdapter(myCursorAdapter);
    } //End of the reference

    //Basic exit activity method
    private void escape()
    {
        backBtn = (Button)findViewById(R.id.backButton2);
        backBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

}
