package coen390.nicholas.coursegradesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    protected static final String TAG = "Main Activity"; //to log the activity for debugging
    protected Button getGrades = null; //button object

    //oncreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "The onCreate() event");
        setupUI(); //setting up the UI
    }

    //function to setup UI
    protected void setupUI()
    {
        //assign object from layout to object in activity
        getGrades = (Button) findViewById(R.id.GetGradesButton);

        //set onClick listenner
        getGrades.setOnClickListener(clickGetGradesButton);
    }

    //function to listen when button is clicked
    private Button.OnClickListener clickGetGradesButton = new Button.OnClickListener()
    {
        //function onClick to override
        public void onClick(View v)
        {
            Log.d(TAG, "The onClick() getGradesButton event");
            Intent intent = new Intent(v.getContext(), GradesActivity.class); //go to gradesactivity class
            startActivity(intent);
        }
    };
}
