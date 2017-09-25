package coen390.nicholas.sharedpreferencesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHelper
{
    private SharedPreferences sharedPreferences; //class object

    //constructor
    public SharePreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE);
    }

    //function to save the name
    public void saveName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileName",name);
        editor.commit();
    }

    //function to get the name
    public String getName()
    {
        return sharedPreferences.getString("profileName",null);
    }

    //function to save the age
    public void saveAge(String age)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileAge",age);
        editor.commit();
    }

    //functino to get the age
    public String getAge()
    {
        return sharedPreferences.getString("profileAge",null);
    }

    //function to save the id
    public void saveID(String id)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileID",id);
        editor.commit();
    }

    //function to get ID
    public String getID()
    {
        return sharedPreferences.getString("profileID",null);
    }
}

