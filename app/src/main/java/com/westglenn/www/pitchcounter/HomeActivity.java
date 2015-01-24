package com.westglenn.www.pitchcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.westglenn.www.pitchcounter.Pitcher;
import java.util.ArrayList;

public class HomeActivity extends Activity {
    private Integer i = 0;
    private ArrayList<Pitcher> list;
    static final String STATE_COUNT = "pitchCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            i = savedInstanceState.getInt(STATE_COUNT);
            list = savedInstanceState.getParcelableArrayList("key");
        } else {
            // Probably initialize members with default values for a new instance
            i = 0;
            list = new ArrayList<Pitcher>();
            // must have 1 pitcher
            Pitcher p = new Pitcher();
            list.add(p);
        }
        Button btnIncrement = (Button) findViewById(R.id.buttonIncrement);
        btnIncrement.setText(i.toString());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_COUNT, i);
        savedInstanceState.putParcelableArrayList("key", list);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void increment(View view) {
        Button btnIncrement = (Button) findViewById(R.id.buttonIncrement);
        i = Integer.parseInt(btnIncrement.getText().toString());
        i++;
        btnIncrement.setText(i.toString());
    }

    public void reset(View view) {
        Button btnIncrement = (Button) findViewById(R.id.buttonIncrement);
        i = 0;
        btnIncrement.setText(i.toString());
    }
}
