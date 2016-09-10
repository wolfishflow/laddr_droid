package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.Posting;

/**
 * Created by greg on 5/8/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task adds a volunteer job Posting to the database.
 */
public class AddPostingTask extends AsyncTask <Posting, Void, Boolean> {

    private Activity activity;

    public AddPostingTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * Adds one Posting to the database.
     * @param postings An array of exactly one Posting.
     * @return Success as a boolean.
     */
    @Override
    protected Boolean doInBackground(Posting... postings) {
        if (postings.length == 0 || postings.length > 1) {
            return false;
        }

        Posting posting = postings[0];
        try {
            return Utility.addPosting(activity, posting);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
