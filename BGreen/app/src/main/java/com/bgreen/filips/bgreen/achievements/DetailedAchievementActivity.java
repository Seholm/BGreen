package com.bgreen.filips.bgreen.achievements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bgreen.filips.bgreen.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedAchievementActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView setAchievementHeadline;
    private TextView setAchievmentDescText;
    private ImageView imageView;
    private String txtReader = "";
    private String subTxtReader = "";
    private Bundle bundle;
    private ProgressBar detailedAchievementPBar;
    private TextView setProgressPercentage;
    private Button getRewardButton;
    private int achievemnt;
    private InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_achievement);
        bundle = getIntent().getExtras();
        displayAchievmentDetails();
    }

    public void displayAchievmentDetails() {
        achievemnt = bundle.getInt("ACHIEVMENT");
        int progress = (int) bundle.getDouble("Progress");

        ReadTextForAchievement r = new ReadTextForAchievement();
        TextView setAchievementHeadline = (TextView) findViewById(R.id.detailedAchievmentHeadText);
        TextView setAchievmentDescText = (TextView) findViewById(R.id.detailedAchievmentText);
        imageView = (ImageView) findViewById(R.id.achievment_detailed_image);
        detailedAchievementPBar = (ProgressBar) findViewById(R.id.detailedAchievementProgressBar);
        setProgressPercentage = (TextView) findViewById(R.id.achievment_progressbar_percentage);
        getRewardButton = (Button) findViewById(R.id.achievment_reward_button);


        StringBuffer sbuffer = new StringBuffer();
        setAchievement(achievemnt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((txtReader = reader.readLine()) != null) {
                    sbuffer.append(txtReader + "\n");
                    subTxtReader = txtReader;
                }

                setAchievementHeadline.setText(r.parseHeadline(subTxtReader));
                setAchievmentDescText.setText(r.parseDescText(subTxtReader));
                detailedAchievementPBar.setProgress(progress);
                setProgressPercentage.setText((Integer.toString(progress)) + "%");
                getRewardButton.setVisibility(View.GONE);
                
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void setAchievement(int image){
        switch (image) {
            case 0:
                Picasso.with(this).load(R.drawable.coffee_cup).into(imageView);
                is = this.getResources().openRawResource(R.raw.achievementlist1);
                break;
            case 1:
                Picasso.with(this).load(R.drawable.air_plane).into(imageView);
                is = this.getResources().openRawResource(R.raw.achievementlist2);
                break;
            case 2:
                Picasso.with(this).load(R.drawable.check_mark).into(imageView);
                is = this.getResources().openRawResource(R.raw.achievementlist3);
                break;
            case 3:
                Picasso.with(this).load(R.drawable.fify_five).into(imageView);
                is = this.getResources().openRawResource(R.raw.achievementlist4);
                break;
            case 4:
                Picasso.with(this).load(R.drawable.road_sign).into(imageView);
                is = this.getResources().openRawResource(R.raw.achievementlist5);
                break;
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

    @Override
    public void onBackPressed(){
        setResult(10);
        finish();
        //super.onBackPressed();
        //closePopUpAchivment.shouldPopupAchivmentBeClosed(true);
    }

    @Override
    public void onClick(View view){
        onBackPressed();
    }
}
