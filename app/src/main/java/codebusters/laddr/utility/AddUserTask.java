package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.User;

/**
 * Created by greg on 5/17/2016.
 */
public class AddUserTask extends AsyncTask<User, Void, Boolean> {

    private static GlobalState globalState;
    private Activity activity;

    public AddUserTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(User... params) {

        if (params.length == 0) {
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
