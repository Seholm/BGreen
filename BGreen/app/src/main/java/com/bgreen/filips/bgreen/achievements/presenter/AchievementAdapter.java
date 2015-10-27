package com.bgreen.filips.bgreen.achievements.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.achievements.service.ImageLoadTask;
import com.bgreen.filips.bgreen.achievements.model.AchievmentRequirements;
import com.bgreen.filips.bgreen.achievements.model.IAchievement;
import com.bgreen.filips.bgreen.achievements.model.IAchievmentRequirements;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.User;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Albertsson on 15-10-26.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementHolder> implements Observer {

    private List<IAchievement> achievements;
    IDisplayAchivment idisplayAchievement;
    IAchievmentRequirements achievmentRequirements;
    IProfile profile;

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

    public AchievementAdapter(List<IAchievement> achievements, IDisplayAchivment idisplayAchievement) {
        this.achievmentRequirements = new AchievmentRequirements();
        this.profile = User.getInstance();
        this.achievements = achievements;
        this.idisplayAchievement = idisplayAchievement; //interface used to not have circulair dependencies,
        // calls AchivmentsFragments displayAchivment method later
    }

    @Override
    public AchievementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_context,
                parent, false);
        AchievementHolder vh = new AchievementHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AchievementHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idisplayAchievement.displayAchievement(position);
            }
        });

        //image for unlocked achievement
        if(achievmentRequirements.checkAchivment(profile, achievements.get(position))){
            new ImageLoadTask(achievements.get(position).getImgURL(), holder.imageView).execute();
        }
        //image for locked achievement
        else{
            new ImageLoadTask(achievements.get(position).getImgURL(), holder.imageView).execute();
            holder.imageView.setAlpha(0.3f);
        }
    }

    @Override
    public int getItemCount() {
            return achievements.size();
        }
}

