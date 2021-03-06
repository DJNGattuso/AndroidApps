package coen390.nicholas.sharedpreferencesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    protected static final String TAG = "MainActivity"; //tag to log events
    protected Button goProfileButton = null; //button declaration
    SharePreferenceHelper sharedPreferences; //object of class

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"The onCreate() event");
        sharedPreferences = new SharePreferenceHelper(this); //create new object
        setupUI();
    }
    protected void setupUI()
    {
        goProfileButton = (Button) findViewById(R.id.getProfile); //link button
    }

    //function to go to profile page when button is pressed
    public void changeToProfile(View view)
    {
        Intent startIntent = new Intent(MainActivity.this, profileActivity.class);
        startActivity(startIntent);
    }


    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"The onStart() event");

        //if there is no saved profile go to profile page to add a profile
        if(sharedPreferences.getName() == null)
        {
            Intent startIntent = new Intent(MainActivity.this, profileActivity.class);
            startActivity(startIntent);
        }
        else //there is a profile
        {
            goProfileButton.setText(sharedPreferences.getName()); //change button text to name
        }
    }

    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"The onStop() event");
    }
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"The onDestroy() event");
    }
}
