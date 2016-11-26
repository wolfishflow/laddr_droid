package codebusters.laddr.modules.forums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import codebusters.laddr.R;
import codebusters.laddr.data.Posting;
import codebusters.laddr.data.Topic;
import codebusters.laddr.modules.postings.PostingsAdapter;

/**
 * Created by alansimon on 2016-11-26.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    ArrayList<Topic> topicList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, firstName, timestamp;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_posting_title);
        }
    }

    public TopicsAdapter(ArrayList<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postings, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new TopicsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopicsAdapter.ViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.title.setText(topic.getTitle());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}