package coen390.nicholas.coursegradesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;


public class GradesActivity extends AppCompatActivity
{
    protected static final String TAG = "Grades Activity"; //to log the activity for debugging
    public int pressed = 0; //track the action button

    //get random number for courses
    Random rnd = new Random();
    int nmdCourse = rnd.nextInt(15) + 1;

    ArrayList<Course> courseList = new ArrayList<>(); //arraylist for courses

    //function for when page gets created
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        Log.d(TAG, "The onCreate event");

        //store courses to courselist
        for (int i = 0; i <= nmdCourse - 1; i++)
        {
            courseList.add(Course.generateRandomCourse());
        }

        setList(courseList); //go to setlist function

    }

    //function to create the listview
    public void setList(ArrayList<Course> object)
    {
        Log.d(TAG, "The setList event");
        ListView gradesList = (ListView) findViewById(R.id.gradesList);

        CourseListAdapter adapter = new CourseListAdapter(this, R.layout.grades_list, object, pressed);
        gradesList.setAdapter(adapter);
    }

    //function for when the action buttom gets pressed
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actions, menu);

        return true;
    }

    //function for when items are selected - code structure, logic and, java functions obtained from tutorial link to developer.android.com
   public boolean onOptionsItemSelected(MenuItem item)
   {
       Log.d(TAG, "The onOptions event");
        switch (item.getItemId())
        {
            /*case android.R.id.home: //the Up button is selected
                NavUtils.navigateUpFromSameTask(this); //go to parent page after the end of the current task
                return true;*/

            //action to switch grades gets pressed
            case R.id.action_changeGrades:
                if (pressed == 0){ pressed = 1;}
                else if (pressed == 1){ pressed = 0;}
                setList(courseList);
                return true;
        }

       return super.onOptionsItemSelected(item);
   }
}
