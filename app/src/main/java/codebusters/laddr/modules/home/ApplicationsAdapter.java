package codebusters.laddr.modules.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import codebusters.laddr.R;
import codebusters.laddr.data.Application;
import codebusters.laddr.data.Comment;

/**
 * Created by alansimon on 2016-11-26.
 */

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ViewHolder> {

    ArrayList<Application> applicationsList;

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

    public ApplicationsAdapter(ArrayList<Application> applicationsList) {
        this.applicationsList = applicationsList;
    }

    @Override
    public ApplicationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_forums, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ApplicationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ApplicationsAdapter.ViewHolder holder, int position) {
        Application application = applicationsList.get(position);
//        holder.title.setText(application.getBody());
//        Picasso.with(holder.itemView.getContext())
//                .load("http://www.laddr.xyz/"+application.getPictureURL())
//                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return applicationsList.size();
    }
}