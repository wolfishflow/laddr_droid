package codebusters.laddr.utility;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import codebusters.laddr.data.Posting;

/**
 * Created by greg on 5/5/2016.
 *
 * This class is used to retrieve an ArrayList of all Postings. The list comes as a JSON object
 * from methods in the Utility class. This is then parsed into a Posting object and added to the list.
 */
public class GetPostingTask extends AsyncTask <Integer, Void, Posting> {

    private final String DEBUG_TAG = "LADDER_DEBUG";

    @Override
    protected Posting doInBackground(Integer... requestedID) {

        Posting posting = null;
        if (requestedID.length == 0) {
            return posting;
        }
        int id = requestedID[0];

        try {
            // There should only be one Posting returned, but it will come wrapped in a JSONArray.
            JSONArray jsonArray = Utility.getPosting(id);
            jsonArray = jsonArray.getJSONArray(0);
            JSONObject json = jsonArray.getJSONObject(0);
            Log.d(DEBUG_TAG, json.toString());

            posting = new Posting();
            posting.setPostingID(Integer.parseInt(json.getString("postingID")));
            posting.setJobTitle(json.getString("jobTitle"));
            posting.setLocation(json.getString("location"));
            posting.setJobDescription(json.getString("description"));
            posting.setOrganizerName(json.getString("organizationName"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posting;
    }
}
