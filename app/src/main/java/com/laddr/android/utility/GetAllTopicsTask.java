package com.laddr.android.utility;

import android.app.Activity;
import android.os.AsyncTask;

import com.laddr.android.data.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by alansimon on 2016-11-26.
 */

public class GetAllTopicsTask extends AsyncTask<Void, Void, ArrayList<Topic>> {

    private final String TAG = "LADDER_DEBUG";
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

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Topic topic = new Topic();
                topic.setTopicId(obj.getString("TopicID"));
                topic.setTitle(obj.getString("Title"));
                topic.setProfileId(obj.getString("ProfileID"));
                if (obj.getJSONObject("LdrProfile").isNull("LdrUsr") && !obj.getJSONObject("LdrProfile").isNull("LdrOrganization")){
                    topic.setFirstName(obj.getJSONObject("LdrProfile").getJSONObject("LdrOrganization").getString("OrganizationName"));
                }else{
                    topic.setFirstName(obj.getJSONObject("LdrProfile").getJSONObject("LdrUser").getString("FirstName"));
                }
                topic.setPicture(obj.getJSONObject("LdrProfile").getString("PictureURL"));
                topic.setTimestamp(obj.getString("Timestamp"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                Date date = format.parse(topic.getTimestamp());
                topics.add(topic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    protected void onPostExecute(ArrayList<Topic> topics) {
        super.onPostExecute(topics);

    }
}
