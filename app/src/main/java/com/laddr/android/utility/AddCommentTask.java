package com.laddr.android.utility;

import android.app.Activity;
import android.os.AsyncTask;

import com.laddr.android.data.Comment;

import org.json.JSONException;

import java.io.IOException;


/**
 * Created by greg on 01/12/16.
 */

public class AddCommentTask extends AsyncTask<Comment, Void, Boolean> {

    Activity activity;

    public AddCommentTask(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected Boolean doInBackground(Comment... params) {

        // Should only add one comment
        if (params.length == 0 || params.length > 1) {
            return false;
        }

        try {
            return Utility.addComment(activity, params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
