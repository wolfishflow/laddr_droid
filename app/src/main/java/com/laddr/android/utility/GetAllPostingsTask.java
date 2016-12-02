package com.laddr.android.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.laddr.android.data.Posting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by greg on 5/3/2016.
 *
 * This class is used to retrieve an ArrayList of all Postings. The list comes as a JSON object
 * from methods in the Utility class. This is then parsed into a Posting object and added to the list.
 */
public class GetAllPostingsTask extends AsyncTask<Void, Void, ArrayList<Posting>> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetAllPostingsTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Posting> doInBackground(Void... params) {

        ArrayList<Posting> postings = new ArrayList<>();

        try {
            //get json of all postings
            JSONArray json = Utility.getAllPostings(activity);
            Log.d("LADDER_DEBUG", json.toString());

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                Posting posting = new Posting();
                posting.setPostingID(obj.getString("PostingID"));
                posting.setJobTitle(obj.getString("JobTitle"));
                posting.setOrganizerName(obj.getJSONObject("LdrProfile").getJSONObject("LdrOrganization").getString("OrganizationName"));
                posting.setPictureLink(obj.getJSONObject("LdrProfile").getString("PictureURL"));
                posting.setLocation(obj.getString("Location"));
                posting.setJobDescription(obj.getString("Description"));
                posting.setEventDate(obj.getString("EventDate"));
                posting.setDeadline(obj.getString("Deadline"));
                posting.setLatitude(obj.optDouble("Lat", 43.58877160000001));
                posting.setLongitude(obj.optDouble("Lng", -79.64439469999999));
                //add new posting to array list
                postings.add(posting);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postings;
    }

    @Override
    protected void onPostExecute(ArrayList<Posting> postings) {
        super.onPostExecute(postings);

    }
}
