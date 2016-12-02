package com.laddr.android.modules.forums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laddr.android.R;
import com.laddr.android.data.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by alansimon on 2016-11-26.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    ArrayList<Comment> commentsList;

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

    public CommentsAdapter(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_forums, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new CommentsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {
        Comment comment = commentsList.get(position);
        holder.title.setText(comment.getBody());
        Picasso.with(holder.itemView.getContext())
                .load("http://www.laddr.xyz/"+comment.getPictureURL())
                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}
