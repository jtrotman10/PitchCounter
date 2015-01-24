package com.westglenn.www.pitchcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;


public class EditPitcherActivity extends Activity {
    private int pitcherId;
    private StateSingleton stateInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateInstance = StateSingleton.getInstance();

        // Get the message from the intent
        Intent intent = getIntent();



        String pitcherName = stateInstance.getCurrentPitcher().getName();


        // Set the text view as the activity layout
        setContentView(R.layout.activity_edit_pitcher);
        EditText editText = (EditText)findViewById(R.id.edit_editPlayer);
        editText.setText(pitcherName);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_pitcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_accept:
                EditText editText = (EditText)findViewById(R.id.edit_editPlayer);
                String pitcherName = editText.getText().toString();

                Intent intent = new Intent();

                intent.putExtra("editpitchername", pitcherName );
                setResult(RESULT_OK, intent);
                finish();

                return true;
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
