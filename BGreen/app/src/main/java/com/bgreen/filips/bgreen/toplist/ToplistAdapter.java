package com.bgreen.filips.bgreen.toplist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.model.IProfile;
import com.bgreen.filips.bgreen.profile.model.ValueTransformer;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * An adapter based on the XML layout fragment_toplist_profile
 *
 * Created by paki on 02/10/15.
 */
public class ToplistAdapter extends RecyclerView.Adapter<ToplistAdapter.TopListHolder> implements Observer {

    private List<IProfile> profiles;
    private Context context;
    private ValueTransformer transformer;
    private IFlipcard flipper; //The Fragment this class should flip when pressed

    @Override
    public void update(Observable observable, Object data) {

    }

    public class TopListHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView personName;
            TextView personDistance;
            TextView personPlacement;
            CircleImageView personPicture;
            CardView cvtest;


            public TopListHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.toplist_fragment1);
                cvtest = (CardView) itemView.findViewById(R.id.card_view);
                personName = (TextView) itemView.findViewById(R.id.toplist_name);
                personDistance = (TextView) itemView.findViewById(R.id.toplist_meter_traveled);
                personPlacement = (TextView) itemView.findViewById(R.id.toplist_hashtag);
                personPicture = (CircleImageView) itemView.findViewById(R.id.toplist_person_image);
            }
    }

    public ToplistAdapter(List<IProfile> profiles, IFlipcard flipper) {
        this.profiles = profiles;
        this.flipper = flipper;
    }

    //Creates the view for the specific layout
    @Override
    public TopListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_toplist_profile,
                parent, false);
        TopListHolder vh = new TopListHolder(v);
        context = parent.getContext();
        transformer = new ValueTransformer();
        return vh;
    }

    //Creates the the components for the topList view and sets an clickListner for the flip animation
    @Override
    public void onBindViewHolder(TopListHolder holder, final int position) {
        holder.cvtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.flipCard(position); //flips the card from the position given from the click
            }
        });
        holder.personName.setText(profiles.get(position).getFirstName() + " " +
                profiles.get(position).getLastName());
        holder.personDistance.setText((transformer.distanceTransformer(profiles.get(position).
                getTotalDistance())));
        //uses the picasso dependencie to load image into the profile picture imageview
        Picasso.with(context).load(profiles.get(position).getImageURL()).into(holder.personPicture);
        holder.personPlacement.setText("#" + Integer.toString(profiles.get(position).getPlacement()));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
