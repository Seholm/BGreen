package com.bgreen.filips.bgreen.toplist;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.profile.IProfile;
import com.bgreen.filips.bgreen.profile.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by paki on 02/10/15.
 */
public class ToplistAdapter extends RecyclerView.Adapter<ToplistAdapter.TopListHolder> {

    private List<IProfile> profiles;
    private Context context;
    //OnItemClickListener clickListener;
    private IFlipcard flipper;

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
                //cvtest.setOnClickListener(this);
            }

            /*@Override
            public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(v);
                System.out.println("hejhej");
            }
            } */
        }

    /*public interface OnItemClickListener {
        void onItemClick(View v);
    } */

    /*public void setOnClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    */

    public ToplistAdapter(List<IProfile> profiles, IFlipcard flipper) {
        this.profiles = profiles;
        this.flipper = flipper;
    }

    @Override
    public TopListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_toplist_profile,
                parent, false); //sista som var tom
        TopListHolder vh = new TopListHolder(v);
        //vh.onClick(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(TopListHolder holder, final int position) {
        holder.cvtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hejhej ********************" + Integer.toString(position));
                flipper.flipCard(position);
            }
        });
        holder.personName.setText(profiles.get(position).getFirstName() + " " +
                    profiles.get(position).getLastName());
        holder.personDistance.setText((Integer.toString(profiles.get(position).getTotalDistance())) + " m");
        Picasso.with(context).load(profiles.get(position).getImageURL()).into(holder.personPicture);
        holder.personPlacement.setText("#" + Integer.toString(profiles.get(position).getPlacement()));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
