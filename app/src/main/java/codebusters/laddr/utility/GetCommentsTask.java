package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import codebusters.laddr.data.Comment;
import codebusters.laddr.data.Posting;
import codebusters.laddr.data.Topic;

/**
 * Created by alansimon on 2016-11-26.
 */

public class GetCommentsTask extends AsyncTask<String, Void, ArrayList<Comment>> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetCommentsTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * Get a Posting by it's PostingID.
     *
     * @param requestedID An array of only one PostingID.
     * @return The requested Posting or null.
     */
    @Override
    protected ArrayList<Comment> doInBackground(String... requestedID) {




        ArrayList<Comment> comments = new ArrayList<>();

        if (requestedID.length != 1) {
            return null;
        }

        Posting posting = null;
        if (requestedID.length == 0) {
            return comments;
        }
        String id = requestedID[0];

        try {
            //get json of all postings
            JSONArray json = Utility.getAllComments(activity, id);
            Log.d("LADDER_DEBUG", json.toString());

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Comment comment = new Comment();
                comment.setCommentId(obj.getJSONObject("comments").getString("CommentID"));
                comment.setProfileId(obj.getJSONObject("comments").getString("ProfileID"));
                comment.setTopicId(obj.getJSONObject("comments").getString("TopicID"));
                comment.setBody(obj.getJSONObject("comments").getString("Body"));
                comment.setTimeStamp(obj.getJSONObject("comments").getString("Timestamp"));
                comment.setBody(obj.getJSONObject("comments").getJSONObject("LdrProfile").getString("PictureURL"));
                //comment.setTitle(obj.getString("Title"));
                comment.setFirstName(obj.getJSONObject("comments").getJSONObject("LdrProfile").getJSONObject("LdrUser").getString("FirstName"));
                //add new posting to array list
                comments.add(comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    protected void onPostExecute(ArrayList<Comment> comment) {
        super.onPostExecute(comment);

    }
}