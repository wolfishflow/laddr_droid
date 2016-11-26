package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import codebusters.laddr.data.GlobalState;

/**
 * Created by alansimon on 2016-11-26.
 */

public class UpdateFdiTask extends AsyncTask<String, Void, Boolean> {

    Activity activity;
    String fdi;
    String id;
    private static GlobalState globalState;

    public UpdateFdiTask(Activity activity, String fdi) {
        this.activity = activity;
        this.fdi = fdi;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
                JSONObject json = (JSONObject) Utility.postRequest(activity, "http://laddr.xyz/api/user/" + fdi);
            boolean success = json.getBoolean("success");
            Log.d("UPDATE_FDI", "Update FDI was successful: " + success);
            return success;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    {
    }
}
