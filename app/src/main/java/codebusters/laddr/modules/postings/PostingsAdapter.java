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

    ArrayList<Posting> postings;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public PostingsAdapter(ArrayList<Posting> postings) {
        this.postings = postings;
    }

    @Override
    public PostingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PostingsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
