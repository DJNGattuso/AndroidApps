package coen390.nicholas.savedataapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class gradeActivity extends AppCompatActivity
{
    protected static final String TAG = "ProfileActivity"; //tag to log events

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Log.d(TAG,"The onCreate() event");
    }

    //function to create the listview
    public void setList(ArrayList<Course> object)
    {
        Log.d(TAG, "The setList event");
        ListView gradesList = (ListView) findViewById(R.id.gradesList);

        CourseListAdapter adapter = new CourseListAdapter(this, R.layout.grades_list, object);
        gradesList.setAdapter(adapter);
    }
}
