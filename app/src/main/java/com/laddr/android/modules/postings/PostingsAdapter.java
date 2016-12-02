package com.laddr.android.modules.postings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laddr.android.R;
import com.laddr.android.data.Posting;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by alansimon on 2016-10-02.
 */

public class PostingsAdapter extends RecyclerView.Adapter<PostingsAdapter.ViewHolder> {

    final static long MILLIS_PER_DAY = 24 * 3600 * 1000;
    ArrayList<Posting> postingsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, orgName,location,timeStamp;
        public ImageView logo;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_posting_title);
            orgName = (TextView) v.findViewById(R.id.tv_posting_organization_name);
            location = (TextView) v.findViewById(R.id.tv_posting_location);
            logo = (ImageView) v.findViewById(R.id.iv_organization_logo);
            timeStamp = (TextView) v.findViewById(R.id.tv_posting_time);
        }
    }

    public PostingsAdapter(ArrayList<Posting> postings) {
        this.postingsList = postings;
    }

    @Override
    public PostingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postings, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostingsAdapter.ViewHolder holder, int position) {
        Posting post = postingsList.get(position);
        holder.title.setText(post.getJobTitle());
        holder.orgName.setText(post.getOrganizerName());
        holder.location.setText(post.getLocation());



        holder.timeStamp.setText(deadlineCalc(post.getDeadline(), post.getEventDate() ));
        Picasso.with(holder.itemView.getContext())
                .load("http://www.laddr.xyz/"+post.getPictureLink())
                .into(holder.logo);
    }


    public String deadlineCalc(String deadline, String event){
        String dateString = "Due Today!";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        Calendar dateToday = Calendar.getInstance();
        Calendar deadlineDate = Calendar.getInstance();
        Calendar dateEvent = Calendar.getInstance();



        try {
            deadlineDate.setTime(format.parse(deadline));
            dateEvent.setTime(format.parse(event));


            long msDiff = deadlineDate.getTimeInMillis() - dateToday.getTimeInMillis();
            long daysDiff = Math.round(msDiff / ((double)MILLIS_PER_DAY));

            if (daysDiff == 0){
                return dateString;
            } else {
                return  String.valueOf(daysDiff)+" days to apply!";
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }

    @Override
    public int getItemCount() {
        return postingsList.size();
    }
}
