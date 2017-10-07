package coen390.nicholas.savedataapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseListAdapter extends ArrayAdapter<Course>
{
    private static final String TAG = "CourseListAdapter"; //tracking tag

    private Context mContext;//variable for context
    int mResource; //variable for resource
    int gradeOption; //variable for grade option

    //custom adapter
    public CourseListAdapter(Context context, int resource, ArrayList<Course> objects, int gradeOptions)
    {
        super(context, resource, objects); //calls existing
        mContext = context;
        mResource = resource;
        gradeOption = gradeOptions;
    }

    //customizing the getView function
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String courseName = getItem(position).getCourseTitle(); //course name
        ArrayList<assignmentToDo> assignments = getItem(position).getAssignments(); //assignments for the course

        Course course = new Course(courseName, assignments); //create course object

        //setting the inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //connecting TextView objects to textView variables
        TextView courseView = (TextView) convertView.findViewById(R.id.listLine1);
        TextView assignmentView = (TextView) convertView.findViewById(R.id.listLine2);
        TextView gradeView = (TextView) convertView.findViewById(R.id.listLine3);

        courseView.setText(courseName); //set the text
        String grades;
        for (int i = 0; i <= assignments.size() - 1; i++)
        {
            assignmentView.append(assignments.get(i).getAssignmentTitle()); //set the assignment text
            assignmentView.append("\n");
            if (gradeOption == 0) //keep grade number
            {
                grades = Integer.toString(assignments.get(i).getAssGrade()); //set grade text
            }
            else //view letters
            {
                grades = assignments.get(i).getLetterGrade(); //set letter grade
            }
            gradeView.append(grades);
            gradeView.append("\n");
        }

        return convertView;
    }
}
