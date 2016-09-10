package codebusters.laddr.utility;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import codebusters.laddr.data.Organization;

/**
 * Created by greg on 5/17/2016.
 *
 * HTTP requests cannot be made on the main UI thread, so must be made asynchronously using
 * AsyncTasks. This task gets an Organization from its ProfileID.
 */
public class GetOrganizationTask extends AsyncTask<String, Void, Organization> {

    public final String DEBUG_TAG = "LADDER_DEBUG";
    private Activity activity;

    public GetOrganizationTask(Activity activity) {
        this.activity = activity;
    }

    /**
     * Gets an Organization from the database
     * @param params The ProfileID of the organization
     * @return The requested organization, or null.
     */
    @Override
    protected Organization doInBackground(String... params) {

        //must have only one param
        if (params.length != 1) {
            return null;
        }

        try {
            JSONObject json = Utility.getOrganization(activity, params[0]);

            Organization organization = new Organization();
            organization.setProfileID(json.getString("ProfileID"));
            organization.setOrganizationName(json.getString("OrganizationName"));
            organization.setAddress(json.getString("Address"));
            organization.setUrl(new URL(json.getString("URL")));
            organization.setMissionStatement(json.getString("MissionStatement"));
            organization.setUsername(json.getString("Username"));
            organization.setEmail(json.getString("Email"));
            organization.setPictureURL(new URL(json.getString("PictureURL")));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
            organization.setTimestamp(formatter.parse(json.getString("Timestamp")));

            organization.setAccountType(Integer.parseInt(json.getString("AccountType")));

            return organization;

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
