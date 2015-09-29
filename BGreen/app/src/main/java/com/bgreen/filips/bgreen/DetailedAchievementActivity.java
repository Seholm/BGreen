package com.bgreen.filips.bgreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedAchievementActivity extends AppCompatActivity {
    private TextView setAchievementHeadline;
    private TextView setAchievmentDescText;
    private CircleImageView circleImageView;
    private String txtReader = "";
    private String subTxtReader = "";
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_achievement);
        bundle = getIntent().getExtras();
        displayAchievmentDetails();
    }

    public void displayAchievmentDetails() {
        int i = bundle.getInt("ACHIEVMENT");
        ReadTextForAchievement r = new ReadTextForAchievement();
        setAchievementHeadline = (TextView) findViewById(R.id.detailedAchievmentHeadText);
        setAchievmentDescText = (TextView) findViewById(R.id.detailedAchievmentText);
        circleImageView = (CircleImageView) findViewById(R.id.achievment_detailed_image);
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.achievementlist + i);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((txtReader = reader.readLine()) != null) {
                    sbuffer.append(txtReader + "\n");
                    subTxtReader = txtReader;
                }
                Picasso.with(this).load(R.drawable.ment_01 + i).into(circleImageView);
                setAchievementHeadline.setText(r.parseHeadline(subTxtReader));
                setAchievmentDescText.setText(r.parseDescText(subTxtReader));
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void getAchievementReward (View view) {
        System.out.println("Display possible rewards");
        //made for the availability to get possible rewards;
        //not yet implemented as it needs cooperation with interested
        //financiers.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_achievement, menu);
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
