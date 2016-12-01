package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.User;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task logs in using a username and password and sets the returned token
 * in our GlobalState.
 */
public class LoginTask extends AsyncTask<String, Void, Boolean> {

    private final String TAG = "Login Task";
    private Activity activity;
    private static GlobalState globalState;


    public LoginTask(Activity activity) {
        this.activity = activity;
    }

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressBar.setVisibility(View.INVISIBLE);
        globalState = (GlobalState) activity.getApplication();
        String profileId = globalState.getUserValue().getProfileID();

        try {
            new GetUserTask(activity).execute(profileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String email = params[0];
        String password = params[1];

        try {
            JSONObject json = Utility.loginProfile(activity, email, password);
            Log.d(TAG, json.toString());

            if (json.getString("success").equals("true")) {
                globalState = (GlobalState) activity.getApplication();
                globalState.setToken(json.getString("token"));
                User user = new User();
                user.setProfileID(json.getJSONObject("profile").getString("ProfileID"));
                user.setEmail(json.getJSONObject("profile").getString("Email"));
                user.setAccountType(Integer.parseInt(json.getJSONObject("profile").getString("AccountType")));
                user.setPicLink(json.getJSONObject("profile").getString("PictureURL"));
                //user.setTimestamp(json.getJSONObject("profile").getString("Timestamp"));
                //broken rn
                //user.setPictureURL(new URL(json.getJSONObject("profile").getString("PictureURL")));

                globalState.setUserValue(user);
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
