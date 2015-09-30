package com.bgreen.filips.bgreen.main;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.SystemClock;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bgreen.filips.bgreen.DetailedAchievementActivity;
import com.bgreen.filips.bgreen.MinuteReciever;
import com.bgreen.filips.bgreen.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.Parse;


public class MainActivity extends AppCompatActivity {

    private final String PARSE_CLIENT_KEY = "0qM0pkPsSmWoEuhqbN4iKHbbSfmgXwLwEJy7ZUHV";
    private final String PARSE_APPLICATION_ID = "Wi3ExMtOI5koRFc29GiaE3C4qmukjPokmETpcPQA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enable Local Datastore, and Parse DB services.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        //sets an alarm with 1 minute interval to run the snipplte code in MinuteReciever
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, MinuteReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1 * 60 * 1000, pendingIntent);

        Intent logginIntent = new Intent(this, LogginActivity.class);
        startActivity(logginIntent);

    }

    public void hermanTest(View v) {
        Intent myIntent = new Intent(MainActivity.this, DetailedAchievementActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
