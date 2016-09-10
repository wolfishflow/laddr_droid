package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.Organization;

/**
 * Created by greg on 5/17/2016.
 */
public class AddOrganizationTask extends AsyncTask<Organization, Void, Boolean> {

    private Activity activity;

    public AddOrganizationTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Organization... params) {

        if (params.length == 0) {
            return false;
        }

        Organization organization = params[0];

        try {
            return Utility.addOrganization(activity, organization);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
