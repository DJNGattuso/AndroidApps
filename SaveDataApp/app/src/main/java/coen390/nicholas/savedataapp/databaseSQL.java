package coen390.nicholas.savedataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class databaseSQL extends SQLiteOpenHelper
{
    //--------------------------------------------Initialize Database-----------------------------------------------------
    private static final String LOG = "Database";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GradesDatabase";

    //-------------------------------------------Setup Fields for Courses-------------------------------------------------
    private static final String TABLE_COURSES = "Courses";
    private static final String KEY_COURSE_ID = "CourseID";
    private static final String KEY_COURSE_TITLE = "CourseTitle";
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE "+
            TABLE_COURSES + "(" + KEY_COURSE_ID + " INTEGER_PRIMARY_KEY," + KEY_COURSE_TITLE + " TEXT" + ")";

    //----------------------------------------Setup Fields for Assignments------------------------------------------------
    private static final String TABLE_ASSIGNMENT = "ASSIGNMENTS";
    private static final String KEY_ASSIGNMENT_ID = "AssID";
    private static final String KEY_ASSIGNMENT_TITLE = "AssTitle";
    private static final String KEY_ASSIGNMENT_GRADE = "Grade";
    private static final String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE "+ TABLE_ASSIGNMENT + "(" + KEY_ASSIGNMENT_ID +
            " INTEGER_PRIMARY_KEY," + KEY_ASSIGNMENT_TITLE + " TEXT" + KEY_ASSIGNMENT_GRADE + " INTEGER" + ")";

    //------------------------------Setup Fields for Combination of Course + Assignments---------------------------------------
    private static final String TABLE_COMBO = "COURSEWITHASSIGNMENTS";
    private static final String CREATE_TABLE_COMBO = "CREATE TABLE "+ TABLE_COMBO + "(" +
            KEY_ASSIGNMENT_ID + " INTEGER" + KEY_COURSE_ID + " INTEGER" + ")";


    //--------------------------------------------Database Constructor-------------------------------------------------------
    public databaseSQL(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //---------------------------------------------On Create Function------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
        db.execSQL(CREATE_TABLE_COMBO);
    }

    //--------------------------------------------On Upgrade Function-------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMBO);
        onCreate(db);
    }

    //----------------------------------------Creating the Assignment Table-------------------------------------------------
    public long createAssignmentTable(assignmentToDo assignmentObject, long course_ids)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ASSIGNMENT_ID, assignmentObject.getAssId());
        values.put(KEY_ASSIGNMENT_TITLE, assignmentObject.getAssignmentTitle());
        values.put(KEY_ASSIGNMENT_GRADE, assignmentObject.getAssGrade());

        long assignment_id = db.insert(TABLE_ASSIGNMENT, null, values);

        createCombo(assignment_id, course_ids);

        return assignment_id;
    }

    //---------------------------------------Creating the Course Table-------------------------------------------------------
    public long createCourseTable(Course course)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE_ID, course.getCourseId());
        values.put(KEY_COURSE_TITLE, course.getCourseTitle());

        long course_id = db.insert(TABLE_COURSES, null, values);
        return course_id;
    }

    //------------------------------------------Creating Combo Table---------------------------------------------------------
    public long createCombo(long assignment_id, long course_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ASSIGNMENT_ID, assignment_id);
        values.put(KEY_COURSE_ID, course_id);

        long id = db.insert(TABLE_COMBO, null, values);
        return id;
    }

    //------------------------------------Function to get assignments from table--------------------------------------------
    public assignmentToDo getAssignment(long assignment_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT + " WHERE " + KEY_ASSIGNMENT_ID + " = " + assignment_id;

        Log.e(LOG,selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null)
        {
            c.moveToFirst();
        }

        assignmentToDo ct = new assignmentToDo();
        ct.setAssID(c.getInt(c.getColumnIndex(KEY_ASSIGNMENT_ID)));
        ct.setAssTitle(c.getString(c.getColumnIndex(KEY_ASSIGNMENT_TITLE)));
        ct.setAssGrade(c.getInt(c.getColumnIndex(KEY_ASSIGNMENT_GRADE)));

        return ct;
    }

    //----------------------------------Function to fetch all assignments------------------------------------------------
    //reference: Cameron Hall -> provided assistance in this function
    public ArrayList<assignmentToDo> getAssCourse(String courseTitle)
    {
        ArrayList<assignmentToDo> allAss = new ArrayList<assignmentToDo>();

        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst())
        {
            while(c.moveToFirst())
            {
                String Courseid = courseTitle.substring(courseTitle.length() - 1, courseTitle.length());
                String Tableid = (c.getString(c.getColumnIndex(KEY_COURSE_ID)));

                if (Courseid.equals(Tableid));
                {
                    assignmentToDo assignments = new assignmentToDo();
                    assignments.setAssTitle(c.getString(c.getColumnIndex(KEY_ASSIGNMENT_TITLE)));
                    assignments.setAssGrade(c.getInt(c.getColumnIndex(KEY_ASSIGNMENT_GRADE)));

                    allAss.add(assignments);
                }
            }
        }
        return allAss;
    }
    //-------------------------------------Function to fetch all courses-------------------------------------------------
    //reference: Cameron Hall -> provided assistance in this function
    public ArrayList<Course> getCourses()
    {
        ArrayList<Course> allCourses = new ArrayList<Course>();

        String selectQuery = "SELECT * FROM " + TABLE_COURSES;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst())
        {
            Course temp = new Course();

            while(c.moveToFirst())
            {
                ArrayList<assignmentToDo> courseAss = getAssCourse(c.getString(c.getColumnIndex(KEY_COURSE_TITLE)));
                temp.setAssi(courseAss);
                temp.setTitle(c.getString(c.getColumnIndex(KEY_COURSE_TITLE)));

                allCourses.add(temp);
            }
        }
        return allCourses;
    }
}
