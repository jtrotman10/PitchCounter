package com.westglenn.www.pitchcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PitcherActivity extends Activity {
    private StateSingleton stateInstance;
    Pitcher p = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stateInstance = StateSingleton.getInstance();

        // Get the message from the intent
        Intent intent = getIntent();

        int pitcherId = stateInstance.getCurrentPitcherId();
        //list = intent.getParcelableArrayListExtra(PitcherListActivity.PITCHER_LIST);
        p = stateInstance.getCurrentPitcher();

        setContentView(R.layout.activity_pitcher);
        TextView tvName = (TextView)findViewById(R.id.tvPitcherName);
        tvName.setText(p.getName());

        TextView tvCount = (TextView)findViewById(R.id.tvPitchCount);
        tvCount.setText("Pitches: " + Integer.toString(p.getPitchCount()));

        TextView tvLastPitch = (TextView)findViewById(R.id.tvLastPitch);
        tvLastPitch.setText("Last: " + p.getFormattedLastPitch());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pitcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_edit:
                //intent = new Intent(this, EditPitcherActivity.class);
                //startActivityForResult(intent, 1);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Edit Pitcher");
                //alert.setMessage(p.getName());

                // Set an EditText view to get user input
                final EditText input = new EditText(this);
                input.setText(p.getName());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        StateSingleton state = StateSingleton.getInstance();
                        state.getCurrentPitcher().setName(input.getText().toString());

                        setContentView(R.layout.activity_pitcher);
                        TextView tvName = (TextView)findViewById(R.id.tvPitcherName);
                        tvName.setText(p.getName());

                        TextView tvCount = (TextView)findViewById(R.id.tvPitchCount);
                        tvCount.setText("Pitches: " + Integer.toString(p.getPitchCount()));

                        TextView tvLastPitch = (TextView)findViewById(R.id.tvLastPitch);
                        tvLastPitch.setText("Last: " + p.getFormattedLastPitch());

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

                return true;
            case R.id.action_discard:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PitcherActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Delete...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want delete this?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_action_discard);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        stateInstance.getList().remove(stateInstance.getCurrentPitcher());
                        stateInstance.setCurrentPitcherId(0);

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        // do nothing
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();

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
                String editPitcherName = data.getStringExtra("editpitchername");
                p = StateSingleton.getInstance().getCurrentPitcher();
                p.setName(editPitcherName);

                int pitcherId = stateInstance.getCurrentPitcherId();
                //list = intent.getParcelableArrayListExtra(PitcherListActivity.PITCHER_LIST);
                p = stateInstance.getCurrentPitcher();

                setContentView(R.layout.activity_pitcher);
                TextView tvName = (TextView)findViewById(R.id.tvPitcherName);
                tvName.setText(p.getName());

                TextView tvCount = (TextView)findViewById(R.id.tvPitchCount);
                tvCount.setText("Pitches: " + Integer.toString(p.getPitchCount()));

                TextView tvLastPitch = (TextView)findViewById(R.id.tvLastPitch);
                tvLastPitch.setText("Last: " + p.getFormattedLastPitch());


            }
        }
    }

    public void increment(View view) {
        Button btnIncrement = (Button) findViewById(R.id.buttonIncrement);
        stateInstance.getCurrentPitcher().Increment();

        TextView tvCount = (TextView)findViewById(R.id.tvPitchCount);
        tvCount.setText("Pitches: " + Integer.toString(p.getPitchCount()));

        TextView tvLastPitch = (TextView)findViewById(R.id.tvLastPitch);
        tvLastPitch.setText("Last: " + p.getFormattedLastPitch());
    }

    public void decrement(View view) {
        Button btnDecrement = (Button) findViewById(R.id.buttonDecrement);
        stateInstance.getCurrentPitcher().Decrement();

        TextView tvCount = (TextView)findViewById(R.id.tvPitchCount);
        tvCount.setText("Pitches: " + Integer.toString(p.getPitchCount()));

        TextView tvLastPitch = (TextView)findViewById(R.id.tvLastPitch);
        tvLastPitch.setText("Last: " + p.getFormattedLastPitch());
    }

}
