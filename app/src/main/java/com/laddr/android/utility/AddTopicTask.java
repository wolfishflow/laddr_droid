package com.laddr.android.utility;

import android.app.Activity;
import android.os.AsyncTask;

import com.laddr.android.data.Comment;
import com.laddr.android.data.Topic;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by greg on 01/12/16.
 */

public class AddTopicTask extends AsyncTask<Void, Void, Boolean> {

    Activity activity;
    Topic topic;
    Comment comment;

    public AddTopicTask(Activity activity, Topic topic, Comment comment) {
        this.activity = activity;
        this.topic = topic;
        this.comment = comment;
    }


    @Override
    protected Boolean doInBackground(Void... params) {

        if (topic == null || comment == null) {
            return false;
        }

        try {
            return Utility.addTopic(activity, topic, comment);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
