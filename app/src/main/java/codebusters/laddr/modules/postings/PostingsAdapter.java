package codebusters.laddr.modules.postings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import codebusters.laddr.R;
import codebusters.laddr.data.Posting;

/**
 * Created by alansimon on 2016-10-02.
 */

public class PostingsAdapter extends RecyclerView.Adapter<PostingsAdapter.ViewHolder> {

    ArrayList<Posting> postingsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, orgName,location;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.postings_title);
            orgName = (TextView) v.findViewById(R.id.postings_organization);
            location = (TextView) v.findViewById(R.id.postings_location);
        }
    }

    public PostingsAdapter(ArrayList<Posting> postings) {
        this.postingsList = postings;
    }

    @Override
    public PostingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_postings, parent, false);
        // set the view's size, margins, paddings and layout parameters


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostingsAdapter.ViewHolder holder, int position) {
        Posting post = postingsList.get(position);
        holder.title.setText(post.getJobTitle());
        holder.orgName.setText(post.getOrganizerName());
        holder.location.setText(post.getLocation());
    }

    @Override
    public int getItemCount() {
        return postingsList.size();
    }
}
