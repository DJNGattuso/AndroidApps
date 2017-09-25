package coen390.nicholas.sharedpreferencesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class profileActivity extends AppCompatActivity
{

    protected static final String TAG = "ProfileActivity"; //tag to log events

    SharePreferenceHelper sharedPreferences; //class object

    //declare variables
    protected EditText editName = null;
    protected EditText editAge = null;
    protected EditText editID = null;
    protected Button saveButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG,"The onCreate() event");

        sharedPreferences = new SharePreferenceHelper(this);

        setupUI();
    }

    protected void setupUI()
    {
        //link to xml
        editName = (EditText) findViewById(R.id.nameText);
        editAge = (EditText) findViewById(R.id.ageText);
        editID = (EditText) findViewById(R.id.IDText);
        saveButton = (Button) findViewById(R.id.SaveButton);

        if(sharedPreferences.getName() != null) //profile exist change text to respected values
        {
            editName.setText(sharedPreferences.getName());
            editAge.setText(sharedPreferences.getAge());
            editID.setText(sharedPreferences.getID());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu); //create the menu action
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(TAG, "The onOptions event");
        switch(item.getItemId())
        {
            case R.id.editProfile: //when the edit option is selected
                saveButton.setVisibility(View.VISIBLE); //show the save button
                //change to edit mode
                editName.setFocusableInTouchMode(true);
                editAge.setFocusableInTouchMode(true);
                editID.setFocusableInTouchMode(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveUpdate(View view)
    {
        //get the inputed values
        String editNameValue = editName.getText().toString();
        String editAgeValue = editAge.getText().toString();
        String editIDValue = editID.getText().toString();

        //if age is greater than 0
        if (editAgeValue.compareTo("0") > 0)
        {
            //save the values
            sharedPreferences.saveAge(editAgeValue);
            sharedPreferences.saveID(editIDValue);
            sharedPreferences.saveName(editNameValue);

            //close the edit mode
            editName.setFocusable(false);
            editAge.setFocusable(false);
            editID.setFocusable(false);

            //show when the save button has been clicked
            Toast toast = Toast.makeText(getApplicationContext(), "Update Saved :)" , Toast.LENGTH_LONG);
            toast.show();
            saveButton.setVisibility(View.INVISIBLE);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Update Unsuccessful :(", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"The onStart() event");

        if(sharedPreferences.getName() != null)
        {
            editName.setFocusable(false);
            editAge.setFocusable(false);
            editID.setFocusable(false);
        }
        else
        {
            saveButton.setVisibility(View.VISIBLE);
        }
    }

    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"The onStop() event");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"The onDestroy() event");
    }
}