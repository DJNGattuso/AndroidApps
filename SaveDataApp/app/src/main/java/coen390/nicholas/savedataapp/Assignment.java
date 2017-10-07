package coen390.nicholas.savedataapp;

//code provided in the assignment instruction - modified/included letter grade

import java.util.Random;

public class Assignment
{
    private static int assID = 0; //static ID increments with every new assignment
    private String assignmentTitle;
    private int assignmentGrade;
    private String letterGrade; //variable for the letter grades

    //private constructor, increment ID
    private Assignment(String title, int grade, String letter)
    {
        assignmentTitle = title;
        assignmentGrade = grade;
        letterGrade = letter;
        assID++;
    }

    //generate random assignment values
    static public Assignment generateRandomAssignment()
    {
        Random rnd = new Random(); //create new random variable
        String tempTitle = "Assignment " + assID; //title of the assignment
        int tempGrade = rnd.nextInt(100) + 1; //get a random grade from 1 - 100
        String tempLetter = null; //temp letter grade variable
        //letter grade breakdown
        if (95 <= tempGrade){ tempLetter = "A+";}
        else if (90 <= tempGrade && tempGrade < 95){ tempLetter = "A";}
        else if (85 <= tempGrade && tempGrade < 90){ tempLetter = "A-";}
        else if (80 <= tempGrade && tempGrade < 85){ tempLetter = "B+";}
        else if (75 <= tempGrade && tempGrade < 80){ tempLetter = "B";}
        else if (70 <= tempGrade && tempGrade < 75){ tempLetter = "B-";}
        else if (67 <= tempGrade && tempGrade < 70){ tempLetter = "C+";}
        else if (64 <= tempGrade && tempGrade < 67){ tempLetter = "C";}
        else if (60 <= tempGrade && tempGrade < 64){ tempLetter = "C-";}
        else if (57 <= tempGrade && tempGrade < 60){ tempLetter = "D+";}
        else if (53 <= tempGrade && tempGrade < 57){ tempLetter = "D";}
        else if (50 <= tempGrade && tempGrade < 53){ tempLetter = "D-";}
        else if (tempGrade < 50){ tempLetter = "F";}

        return new Assignment(tempTitle, tempGrade, tempLetter);
    }

    public String getAssignmentTitle() {return assignmentTitle;}
    public int getAssignmentGrade() {return assignmentGrade;}
    public String getLetterGrade(){return  letterGrade;} //get letter grade
}
