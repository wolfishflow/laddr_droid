package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import codebusters.laddr.data.Application;

/**
 * Created by greg on 01/12/16.
 */

public class GetApplicationTask extends AsyncTask<Void, Void, ArrayList<Application>> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    @Override
    protected ArrayList<Application> doInBackground(Void... params) {
        ArrayList<Application> applications = new ArrayList<>();

        try {
            //get json of all postings
            JSONObject jsonResponse = Utility.getApplications(activity);
            JSONArray json = jsonResponse.getJSONArray("applications");
            Log.d("LADDER_DEBUG", json.toString());

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                Application app = new Application();
                app.setJobTitle(obj.getJSONObject("LdrPosting").getString("JobTitle"));
                app.setPostingId(obj.getJSONObject("LdrPosting").getString("PostingID"));
                app.setOrganizationName(obj.getJSONObject("LdrPosting").getJSONObject("LdrProfile")
                        .getJSONObject("LdrOrganization").getString("OrganizationName"));
                app.setOrganizationProfileId(obj.getJSONObject("LdrPosting").getJSONObject("LdrProfile").getString("ProfileID"));
                app.setOrganizationPhoto(obj.getJSONObject("LdrPosting").getJSONObject("LdrProfile")
                        .getString("PictureURL"));
                app.setStatus(obj.getInt("ApplicationStatus"));

                //add new application to array list
                applications.add(app);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return applications;
    }
}
