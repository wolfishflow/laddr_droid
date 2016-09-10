package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import codebusters.laddr.data.Organization;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task adds an Organization to the database (organization sign up).
 */
public class AddOrganizationTask extends AsyncTask<Organization, Void, Boolean> {

    private Activity activity;

    public AddOrganizationTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * Adds an Organization to the database.
     * @param params An array of exactly one Organization object
     * @return Success as a boolean
     */
    @Override
    protected Boolean doInBackground(Organization... params) {

        if (params.length == 0 || params.length > 1) {
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
