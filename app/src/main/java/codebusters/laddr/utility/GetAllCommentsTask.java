package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import codebusters.laddr.data.Comment;

/**
 * Created by alansimon on 2016-11-26.
 */

public class GetAllCommentsTask extends AsyncTask<String, Void, ArrayList<Comment>> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetAllCommentsTask(Activity activity) {
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

        if (requestedID.length == 0) {
            return comments;
        }
        String id = requestedID[0];

        try {
            //get json of all comments
            JSONObject json = Utility.getAllComments(activity, id);
            JSONArray commentArray = json.getJSONArray("comments");

            //iterate through the array and create a comment object for each
            for (int i = 0; i < commentArray.length(); i++) {
                JSONObject obj = commentArray.getJSONObject(i);
                Comment comment = new Comment();
                comment.setCommentId(obj.getString("CommentID"));
                comment.setProfileId(obj.getString("ProfileID"));
                comment.setTopicId(obj.getString("TopicID"));
                comment.setBody(obj.getString("Body"));
                comment.setTimeStamp(obj.getString("Timestamp"));
                comment.setPictureURL(obj.getJSONObject("LdrProfile").getString("PictureURL"));
                comment.setFirstName(obj.getJSONObject("LdrProfile").getJSONObject("LdrUser").getString("FirstName"));
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