package com.westglenn.www.pitchcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Selection;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.TextView;

public class PitcherListActivity extends Activity {
    private StateSingleton stateInstance;
    private ArrayAdapter<Pitcher> adapter = null;

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            StateSingleton state = StateSingleton.getInstance();

            Pitcher p = (Pitcher) parent.getItemAtPosition(position);
            state.setCurrentPitcherId(p.getId());
            Intent intent = new Intent(parent.getContext(), PitcherActivity.class);

            //intent.putExtra(PITCHER_ID, p.getId());
            //intent.putParcelableArrayListExtra(PITCHER_LIST, list);
            //startActivity(intent);
            startActivityForResult(intent, 2);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitcher_list);

        /*if (savedInstanceState != null) {
            // Restore value of members from saved state
            list = savedInstanceState.getParcelableArrayList("key");
        } else {
            // Probably initialize members with default values for a new instance
            list = PitcherListSingleton.getInstance();
        }*/
        stateInstance = StateSingleton.getInstance();

        adapter = new ArrayAdapter<Pitcher>(this,
                android.R.layout.simple_list_item_1, stateInstance.getList());
        ListView listView = (ListView) findViewById(R.id.listPitchers);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        //savedInstanceState.putParcelableArrayList("key", list);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pitcher_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_add:
                //intent = new Intent(this, AddPitcherActivity.class);
                //intent.putParcelableArrayListExtra(PITCHER_LIST, list);
                //startActivityForResult(intent, 1);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Add Pitcher");
                //alert.setMessage(p.getName());

                // Set an EditText view to get user input
                final EditText input = new EditText(this);
                Integer count = stateInstance.getList().size() + 1;
                String pitcherName = String.format("Pitcher %d", count);

                input.setText(pitcherName);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        StateSingleton state = StateSingleton.getInstance();
                        Pitcher p = new Pitcher();
                        p.setName(input.getText().toString());
                        state.getList().add(p);
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String newPitcherName = data.getStringExtra("newpitchername");
                Pitcher p = new Pitcher();
                p.setName(newPitcherName);
                stateInstance.getList().add(p);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}