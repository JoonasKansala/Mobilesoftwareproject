package joonas.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class InsertActivity extends Activity {

    //Initialise database adapter and widgets;
    DatabaseAdapter DB;
    Button insertBtn, backBtn, deleteBtn,updateBtn;
    RatingBar rating;
    EditText artistInsert, albumInsert, trackInsert, yearInsert, idField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using this method for removing the title and making the activity view fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_insert);

        //Created an object of database adapter. Opening up the database;
        DB = new DatabaseAdapter(this);
        DB.open();

        //Type casting the widgets
        insertBtn = (Button)findViewById(R.id.insert_button); //Insert data to database
        backBtn = (Button)findViewById(R.id.back_button_one); //Exit activity
        deleteBtn = (Button)findViewById(R.id.delete_button); //Delete a row from database
        updateBtn = (Button)findViewById(R.id.update_button); //Update a row in database
        artistInsert = (EditText)findViewById(R.id.insert_artist); //Add artist details
        albumInsert = (EditText)findViewById(R.id.insert_album); //Add album details
        trackInsert = (EditText)findViewById(R.id.insert_track); //Add track details
        yearInsert = (EditText)findViewById(R.id.insert_year); //Add year details
        idField = (EditText)findViewById(R.id.id_edit); //Use this field only for updating and deleting a row
        rating = (RatingBar)findViewById(R.id.ratingbar); //Give a rating

        //All initialised methods created for this activity
        addData();
        deleteMusicData();
        updateData();
        Return();
    }

    //http://www.youtube.com/playlist?list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07 <-- Reference used for these methods

    //If you insert data into the database, this method is being used
    public void addData()
    {
        insertBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = DB.insertMusicData(artistInsert.getText().toString()
                                , albumInsert.getText().toString()
                                , trackInsert.getText().toString()
                                , yearInsert.getText().toString()
                                , rating.getRating());
                        if(isInserted == true)
                            Toast.makeText(InsertActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(InsertActivity.this, "Data was not inserted", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    //If you want to delete a row in your database, this method gets launched
    public void deleteMusicData()
    {
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer delRows = DB.deleteMusicRow(idField.getText().toString());
                        if(delRows > 0)
                            Toast.makeText(InsertActivity.this,"Data deleted",Toast.LENGTH_LONG).show(); //If one or more rows gets deleted, this message launches
                        else
                            Toast.makeText(InsertActivity.this,"Data not deleted",Toast.LENGTH_LONG).show(); //If none of the rows gets deleted, this message launches
                    }
                }
        );
    }

    //If you want to update a row in your database, this method launches
    public void updateData()
    {
        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = DB.updateMusicData(idField.getText().toString()
                                , artistInsert.getText().toString()
                                , albumInsert.getText().toString()
                                , trackInsert.getText().toString()
                                , yearInsert.getText().toString()
                                , rating.getRating());
                        if (isUpdated == true)
                            Toast.makeText(InsertActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(InsertActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //End of reference;

    //If you want to exit the activity, this method launches
    public void Return()
    {
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
