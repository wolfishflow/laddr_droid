package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import codebusters.laddr.data.Posting;

/**
 * Created by greg on 5/5/2016.
 *
 * This class is used to retrieve an ArrayList of all Postings. The list comes as a JSON object
 * from methods in the Utility class. This is then parsed into a Posting object and added to the list.
 */
public class GetPostingTask extends AsyncTask <String, Void, Posting> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetPostingTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Posting doInBackground(String... requestedID) {

        Posting posting = null;
        if (requestedID.length == 0) {
            return posting;
        }
        String id = requestedID[0];

        try {
            JSONObject json = Utility.getPosting(activity, id);
            Log.d(DEBUG_TAG, json.toString());

            posting = new Posting();
            posting.setPostingID(json.getString("PostingID"));
            posting.setJobTitle(json.getString("JobTitle"));
            posting.setLocation(json.getString("Location"));
            posting.setJobDescription(json.getString("Description"));
            posting.setOrganizerName(json.getString("OrganizationName"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posting;
    }
}
