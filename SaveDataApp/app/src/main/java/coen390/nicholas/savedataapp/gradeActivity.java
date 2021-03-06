package coen390.nicholas.savedataapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class gradeActivity extends AppCompatActivity
{
    protected static final String TAG = "gradeActivity"; //tag to log events
    protected ListView gradesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Log.d(TAG,"The onCreate() event");

        databaseSQL db = new databaseSQL(getApplicationContext());
        ArrayList<Course> allCourses = db.getCourses();

        setList(allCourses);
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
