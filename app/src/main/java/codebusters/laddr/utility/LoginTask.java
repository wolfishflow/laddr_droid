package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import codebusters.laddr.data.GlobalState;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task logs in using a username and password and sets the returned token
 * in our GlobalState.
 */
public class LoginTask extends AsyncTask<String, Void, Boolean> {

    private final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;
    private static GlobalState globalState;

    public LoginTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * AsyncTask that takes in username and password, and gets the token associated with that user.
     * @param params Pass in two strings: username then password.
     * @return Success as a boolean.
     */
    @Override
    protected Boolean doInBackground(String... params) {
        //need 2 parameters
        if (params.length < 2) {
            return null;
        }
        String username = params[0];
        String password = params[1];

        try {
            JSONObject json = Utility.loginProfile(activity, username, password);
            Log.d(DEBUG_TAG, json.toString());

            if (json.getString("success").equals("true")) {
                globalState = (GlobalState) activity.getApplication();
                globalState.setToken(json.getString("token"));
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
