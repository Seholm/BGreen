package com.bgreen.filips.bgreen.achievements;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bgreen.filips.bgreen.R;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Albertsson on 15-10-26.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementHolder> implements Observer {

    private List<IAchievement> achievements;
    private Context context;
    AchievementFragment achievementFragment;
    Map<String,Boolean> unlockedAchievements;
    Map<String,Double> achievementProgress;

    @Override
    public void update(Observable observable, Object data) {
    }

    public class AchievementHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

         public AchievementHolder(View itemView) {
             super(itemView);
             imageView = (ImageView) itemView.findViewById(R.id.achivement_imageView_01);
         }
    }

    public AchievementAdapter(List<IAchievement> achievements, AchievementFragment achievementFragment,
                              Map<String,Boolean> unlockedAchievements, Map<String,Double> achievementProgress) {
        this.achievements = achievements;
        this.achievementFragment = achievementFragment;
        this.unlockedAchievements = unlockedAchievements;
        this.achievementProgress = achievementProgress;
    }

    @Override
    public AchievementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_context,
                parent, false);
        AchievementHolder vh = new AchievementHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(AchievementHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hejhej ********************" + Integer.toString(position));
                achievementFragment.displayAchievement(position);
            }
        });

        String achievement = "Achievement" + (position);

        //if there is no more achievementList don't load an image
        if(unlockedAchievements.get(achievement)!=null){
            //image for unlocked achievement
            if(unlockedAchievements.get(achievement)==true){
                new ImageLoadTask(achievements.get(position).getImgURL(), holder.imageView).execute();
            }
            //image for locked achievement
            else{
                new ImageLoadTask(achievements.get(position).getImgURL(), holder.imageView).execute();
                holder.imageView.setAlpha(0.3f);
            }
        }
    }

    @Override
    public int getItemCount() {
            return achievements.size();
        }
}

