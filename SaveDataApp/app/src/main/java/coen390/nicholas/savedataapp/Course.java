package coen390.nicholas.savedataapp;


//code provided in the assignment instruction

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Course
{
    private static int courseID = 0;
    private String courseTitle;
    private ArrayList<assignmentToDo> assignments;
    private String letter;

    public Course(String title, ArrayList<assignmentToDo> assns)
    {
        courseTitle = title;
        assignments = assns;
        courseID++;
    }

    static public Course generateRandomCourse()
    {
        Random rnd = new Random();
        int assignmentNo = rnd.nextInt(5) + 1;
        ArrayList<assignmentToDo> tempAssns = new ArrayList<assignmentToDo>();

        for (int i = 0; i < assignmentNo; i++)
        {
            tempAssns.add(assignmentToDo.generateRandomAssignment());
        }

        return new Course("Course " + courseID, tempAssns);
    }

    public String getCourseTitle() {return  courseTitle;}
    public ArrayList<assignmentToDo> getAssignments() {return  assignments;}
}
