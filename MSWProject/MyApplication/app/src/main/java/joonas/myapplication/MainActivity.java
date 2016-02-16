/*
Created by Joonas Känsälä

7.11.2015
 */


package joonas.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Initialise buttons, create an object of database adapter straight away
    Button insertActivityBtn, viewActivityBtn;
    DatabaseAdapter DB = new DatabaseAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Used this method to make activity view on fullscreen, doesn't work for some reason
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Type casting the buttons
        insertActivityBtn = (Button)findViewById(R.id.insertActivity); //Go to data handling activity
        viewActivityBtn = (Button)findViewById(R.id.ViewActivity); //Go to data viewing activity

        //Open up the database, get writable database
        DB.open();

        //Methods that are being used in this activity
        goToHandleActivity();
        goToViewActivity();
    }

    //If you want to go to the data handling activity, this method fires up
    public void goToHandleActivity()
    {
        insertActivityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeToHandleActivity();
                    }
                }
        );
    }

    //If you want to go to the data viewing activity, this method fires up
    public void goToViewActivity()
    {
        viewActivityBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeToViewActivity();
                    }
                }
        );
    }

    public void changeToHandleActivity()
    {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }

    public void changeToViewActivity()
    {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }
}

