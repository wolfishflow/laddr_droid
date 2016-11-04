package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.Posting;

/**
 * Created by alansimon on 2016-10-16.
 */

public class ApplyPosting extends AsyncTask<Posting, Void, Boolean> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public ApplyPosting(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected Boolean doInBackground(Posting... postings) {
        if (postings.length == 0 || postings.length > 1) {
            return false;
        }

        Posting posting = postings[0];
        try {
            return Utility.applyPosting(activity, posting);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}
