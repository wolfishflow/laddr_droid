package com.laddr.android.modules.forums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laddr.android.R;
import com.laddr.android.data.Topic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by alansimon on 2016-11-26.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    ArrayList<Topic> topicList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, firstName, timestamp;
        public ImageView picture;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_forum_topic);
            picture = (ImageView) v.findViewById(R.id.iv_forum_avatar);
        }
    }

    public TopicsAdapter(ArrayList<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_forums, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new TopicsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopicsAdapter.ViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.title.setText(topic.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load("http://www.laddr.xyz/"+topic.getPicture())
                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}
