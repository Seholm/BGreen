package com.bgreen.filips.bgreen.achievements.presenter;

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
import com.bgreen.filips.bgreen.achievements.model.AchievementHolder;
import com.bgreen.filips.bgreen.achievements.model.AchievmentRequirements;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.bgreen.filips.bgreen.achievements.model.IAchievmentRequirements;
import com.bgreen.filips.bgreen.achievements.service.AchievementService;
import com.bgreen.filips.bgreen.achievements.service.IAchievementService;
import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.profile.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailedAchievementActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Bundle bundle;
    private ProgressBar detailedAchievementPBar;
    private TextView setProgressPercentage;
    private Button getRewardButton;
    private int achievemntPosition;

    private IAchievementService achievementService = new AchievementService();
    private IAchievement achievement;
    private AchievementHolder achievementHolder = AchievementHolder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_achievement);
        if (achievementHolder.getAchievementList().size() == 0 ||
                achievementHolder.getAchievementList() == null){
            try{
                achievementHolder.setAchievementList(achievementService.getAllAchievements());
            }catch (Exception e){
                //TODO: fixa display error
            }
        }
        achievementHolder.getAchievementList();
        bundle = getIntent().getExtras();
        achievemntPosition = bundle.getInt("ACHIEVMENT");
        achievement = achievementHolder.getAchievementList().get(achievemntPosition);

        displayAchievmentDetails();
    }

    public void displayAchievmentDetails() {

        IProfile profile = User.getInstance();
        IAchievmentRequirements achievmentRequirements = new AchievmentRequirements();

        TextView setAchievementHeadline = (TextView) findViewById(R.id.detailedAchievmentHeadText);
        TextView setAchievmentDescText = (TextView) findViewById(R.id.detailedAchievmentText);
        imageView = (ImageView) findViewById(R.id.achievment_detailed_image);
        detailedAchievementPBar = (ProgressBar) findViewById(R.id.detailedAchievementProgressBar);
        setProgressPercentage = (TextView) findViewById(R.id.achievment_progressbar_percentage);
        getRewardButton = (Button) findViewById(R.id.achievment_reward_button);


        setAchievementHeadline.setText(achievement.getTitle());
        setAchievmentDescText.setText(achievement.getDescription());
        System.out.println(profile.getBusTrips() + " " + achievement.getRequirement());

        double progres = achievmentRequirements.checkAchivmentProgress(profile, achievement);
        System.out.println(progres);
        int progress = (int)Math.round(achievmentRequirements.checkAchivmentProgress(profile, achievement));

        detailedAchievementPBar.setProgress(progress);
        setProgressPercentage.setText((Integer.toString(progress)) + "%");
        Picasso.with(this).load(achievement.getImgURL()).into(imageView);
        getRewardButton.setVisibility(View.GONE);

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
    }

    @Override
    public void onClick(View view){
        onBackPressed();
    }
}
