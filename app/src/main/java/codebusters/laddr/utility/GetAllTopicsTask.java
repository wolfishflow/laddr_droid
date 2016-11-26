package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import codebusters.laddr.data.Topic;

/**
 * Created by alansimon on 2016-11-26.
 */

public class GetAllTopicsTask extends AsyncTask<Void, Void, ArrayList<Topic>> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetAllTopicsTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Topic> doInBackground(Void... params) {

        ArrayList<Topic> topics = new ArrayList<>();

        try {
            //get json of all postings
            JSONArray json = Utility.getAllTopics(activity);
            Log.d("LADDER_DEBUG", json.toString());

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                Topic topic = new Topic();
                topic.setTopicId(obj.getString("TopicID"));
                topic.setTitle(obj.getString("Title"));
                topic.setProfileId(obj.getString("ProfileID"));
                topic.setFirstName(obj.getJSONObject("LdrProfile").getJSONObject("LdrUser").getString("FirstName"));
                //add new posting to array list
                topics.add(topic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    protected void onPostExecute(ArrayList<Topic> topics) {
        super.onPostExecute(topics);

    }
}
