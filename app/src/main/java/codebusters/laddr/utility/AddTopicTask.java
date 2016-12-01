package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.Comment;
import codebusters.laddr.data.Posting;
import codebusters.laddr.data.Topic;

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
