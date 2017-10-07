package coen390.nicholas.savedataapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class gradeActivity extends AppCompatActivity
{
    protected static final String TAG = "ProfileActivity"; //tag to log events

    //declare variables
    protected TextView testName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Log.d(TAG,"The onCreate() event");

        setupUI();
    }

    protected void setupUI()
    {
        //link to xml
        testName = (TextView) findViewById(R.id.testID);
    }
}
