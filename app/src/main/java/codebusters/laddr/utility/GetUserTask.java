package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.User;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task gets a user from the ProfileID.
 */
public class GetUserTask extends AsyncTask<String, Void, User> {

    public final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;
    private static GlobalState globalState;


    public GetUserTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected User doInBackground(String... params) {

        //must have only one param
        if (params.length != 1) {
            return null;
        }

        try {
            JSONObject json = Utility.getUser(activity, params[0]);

            //Log.d(DEBUG_TAG, json.toString());

            User user = new User();
            user.setProfileID(json.getString("ProfileID"));
            user.setFirstName(json.getString("FirstName"));
            user.setLastName(json.getString("LastName"));
            user.setUserDescription(json.getString("Description"));
            user.setResume(json.getString("Resume"));
            user.setAcademicStatus(Integer.parseInt(json.getString("AcademicStatus")));
            //user.setEmail(json.getString("Email"));

//            Picture not handled currently
//            user.setPictureURL(new URL(json.getString("FirstName")));

//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
//            user.setTimestamp(formatter.parse(json.getString("Timestamp")));
//
//            user.setAccountType(Integer.parseInt(json.getString("AccountType")));

            globalState = (GlobalState) activity.getApplication();

            globalState.getUserValue().setFirstName(json.getString("FirstName"));
            globalState.getUserValue().setLastName(json.getString("LastName"));
            globalState.getUserValue().setUserDescription(json.getString("Description"));
            globalState.getUserValue().setResume(json.getString("Resume"));
            globalState.getUserValue().setAcademicStatus(Integer.parseInt(json.getString("AcademicStatus")));

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
