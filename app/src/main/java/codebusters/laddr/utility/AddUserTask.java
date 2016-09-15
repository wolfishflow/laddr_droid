package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.User;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task adds a User to the database
 */
public class AddUserTask extends AsyncTask<User, Void, Boolean> {

    private Activity activity;

    public AddUserTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * Add a user to the database.
     * @param params An array of exactly one User.
     * @return Success as a boolean
     */
    @Override
    protected Boolean doInBackground(User... params) {

        if (params.length == 0 || params.length > 1) {
            return false;
        }

        User user = params[0];

        try {
            return Utility.addUser(activity, user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
