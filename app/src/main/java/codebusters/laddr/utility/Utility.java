package codebusters.laddr.utility;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;

import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Organization;
import codebusters.laddr.data.Posting;
import codebusters.laddr.data.SignUpUser;
import codebusters.laddr.data.User;

/**
 * Created by greg on 5/3/2016.
 *
 * The Utility class makes HTTP requests to the API. It has methods for GET requests (with and without
 * parameters) and POST requests, as well as specific methods that get one user, get all postings,
 * add an organization, etc.
 *
 * I used AbstractMap.SimpleEntry<String, String> a lot here. It's just a Key Value pair, which comes
 * up often when making HTTP requests, setting headers and parameters. Maybe we should write our own
 * simple KeyValuePair class.
 */
public class Utility {

    /**
     * A GET request to the specified URL.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param urlString The URL we're requesting
     * @return A JSONArray or JSONObject read from the URL.
     * @throws IOException
     * @throws JSONException
     */

    private static GlobalState globalState;

    public static Object getRequest(Activity activity, String urlString) throws IOException, JSONException {

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add headers
        con.setRequestMethod("GET");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        con.setUseCaches( false );

        // add token header
        globalState = (GlobalState) activity.getApplication();
        con.setRequestProperty("x-access-token", globalState.getToken());

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1) {
            sb.append((char) cp);
        }

        String jsonText = sb.toString();
        Object json = new JSONTokener(jsonText).nextValue();
        if (json instanceof JSONObject) {
            return new JSONObject(jsonText);
        } else if (json instanceof JSONArray) {
            return new JSONArray(jsonText);
        }
        return null;
    }

    /**
     * A GET request to the specified URL, with GET parameters as a Key,Value pairs
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static Object getRequest(Activity activity, String url, AbstractMap.SimpleEntry<String, String>... params)
            throws IOException, JSONException {
        //prepare the URL
        StringBuilder preparedURL = new StringBuilder();
        preparedURL.append(url);
        for (int i = 0; i < params.length; i++) {
            preparedURL.append("/" + params[i].getValue());
            if (i < params.length - 1) {
                preparedURL.append("&");
            }
        }

        return getRequest(activity, preparedURL.toString());
    }

    /**
     * A POST request to the specified URL.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param urlString The requested URL
     * @param params SimpleEntries as Key Value pairs, to be used as POST parameters and values.
     * @return A JSON array read from the URL.
     */
    public static Object postRequest(Activity activity, String urlString, AbstractMap.SimpleEntry<String, String>... params)
        throws IOException, JSONException{

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            AbstractMap.SimpleEntry<String, String> param = params[i];
            sb.append(param.getKey() + "=" + param.getValue());
            if (i < (params.length - 1)) {
                sb.append("&");
            }
        }
        String urlParams = sb.toString();
        byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Send post request
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
        con.setRequestProperty("Accept","*/*");
        con.setUseCaches( false );

        // add token header
        globalState = (GlobalState) activity.getApplication();
        con.setRequestProperty("x-access-token", globalState.getToken());

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(postData);
        wr.flush();
        wr.close();

        Log.d("utility", Integer.toString(con.getResponseCode()));
//        BufferedReader er = new BufferedReader(new InputStreamReader(con.getErrorStream(), Charset.forName("UTF-8")));
//
//        sb = new StringBuilder();
//        int cp;
//        while ((cp = er.read()) != -1) {
//            sb.append((char) cp);
//        }
//        String jsonText = sb.toString();
//        Log.d("utility", jsonText);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));

        sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1) {
            sb.append((char) cp);
        }

        String jsonText = sb.toString();
        jsonText = sb.toString();
        Object json = new JSONTokener(jsonText).nextValue();
        if (json instanceof JSONObject) {
            return new JSONObject(jsonText);
        } else if (json instanceof JSONArray) {
            return new JSONArray(jsonText);
        }
        return null;
    }

    /**
     * Gets all the active Postings from the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @return A JSON array of all active postings
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray getAllPostings(Activity activity) throws IOException, JSONException {
        JSONArray json = (JSONArray)getRequest(activity, "http://laddr.xyz/api/posting");

        return json;
    }

    public static JSONArray getAllTopics(Activity activity) throws IOException, JSONException {
        JSONArray json = (JSONArray)getRequest(activity, "http://laddr.xyz/api/topic");
        return json;
    }

    public static JSONArray getAllComments(Activity activity, String id) throws IOException, JSONException {
        JSONArray json = (JSONArray)getRequest(activity, "http://laddr.xyz/api/topic/"+id);
        return json;
    }

    /**
     * Gets a specified User from the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param id ID of the specified User.
     * @return A JSON array containing the specified User.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject getUser(Activity activity, String id) throws IOException, JSONException {
        AbstractMap.SimpleEntry<String, String> requestedID = new AbstractMap.SimpleEntry<String, String>("ProfileID", id);
        JSONObject json = (JSONObject) getRequest(activity, "http://laddr.xyz/api/user", requestedID);
        return json;
    }

    /**
     * Gets a specified Organization from the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param id ID of the specified Organization
     * @return A JSON array containing the specified Organization
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject getOrganization(Activity activity, String id) throws IOException, JSONException {
        AbstractMap.SimpleEntry<String, String> requestedID = new AbstractMap.SimpleEntry<String, String>("ProfileID", id);
        JSONObject json = (JSONObject) getRequest(activity, "http://laddr.xyz/api/organization", requestedID);
        return json;
    }

    /**
     * Gets a specified posting
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param id ID of the specified posting.
     * @return A JSON array containing the specified posting.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject getPosting(Activity activity, String id) throws IOException, JSONException {
        AbstractMap.SimpleEntry<String, String> requestedID = new AbstractMap.SimpleEntry<String, String>("PostingID", id);
        JSONObject json = (JSONObject) getRequest(activity, "http://laddr.xyz/api/posting", requestedID);
        return json;
    }

    /**
     * Adds a user to the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param su The User object to add.
     * @return Success as a boolean value.
     * @throws IOException
     * @throws JSONException
     */

    public static boolean addNewUser(Activity activity, SignUpUser su) throws IOException, JSONException{

        AbstractMap.SimpleEntry<String, String> firstName = new AbstractMap.SimpleEntry<String, String>("FirstName", su.getFirstName());
        AbstractMap.SimpleEntry<String, String> lastName = new AbstractMap.SimpleEntry<String, String>("LastName", su.getLastName());
        AbstractMap.SimpleEntry<String, String> password = new AbstractMap.SimpleEntry<String, String>("Password", su.getPassword());
        AbstractMap.SimpleEntry<String, String> email = new AbstractMap.SimpleEntry<String, String>("Email", su.getEmail());
        AbstractMap.SimpleEntry<String, String> academicStatus = new AbstractMap.SimpleEntry<String, String>("AcademicStatus", Integer.toString(su.getAcademicStatus()));
        AbstractMap.SimpleEntry<String, String> description = new AbstractMap.SimpleEntry<String, String>("Description", " ");
        AbstractMap.SimpleEntry<String, String> pictureURL = new AbstractMap.SimpleEntry<String, String>("Picture", " ");
        AbstractMap.SimpleEntry<String, String> resume = new AbstractMap.SimpleEntry<String, String>("Resume", " ");



        JSONObject json = (JSONObject) postRequest(activity, "http://laddr.xyz/api/user",
                firstName, lastName, password, email, academicStatus, description, pictureURL, resume);

        Log.d("LADDER_DEBUG", json.toString());

        String result = json.getString("success");
        if (result.equals("true")) {
            return true;
        }

        return false;
    }

    public static boolean addUser(Activity activity, User user) throws IOException, JSONException {

        //TODO: Add validation

        AbstractMap.SimpleEntry<String, String> username = new AbstractMap.SimpleEntry<String, String>("Username", user.getUsername());
        AbstractMap.SimpleEntry<String, String> firstName = new AbstractMap.SimpleEntry<String, String>("FirstName", user.getFirstName());
        AbstractMap.SimpleEntry<String, String> lastName = new AbstractMap.SimpleEntry<String, String>("LastName", user.getLastName());
        AbstractMap.SimpleEntry<String, String> password = new AbstractMap.SimpleEntry<String, String>("Password", user.getPassword());
        AbstractMap.SimpleEntry<String, String> email = new AbstractMap.SimpleEntry<String, String>("Email", user.getEmail());
        AbstractMap.SimpleEntry<String, String> description = new AbstractMap.SimpleEntry<String, String>("Description", user.getUserDescription());
        AbstractMap.SimpleEntry<String, String> resume = new AbstractMap.SimpleEntry<String, String>("Resume", user.getResume());
        AbstractMap.SimpleEntry<String, String> academicStatus = new AbstractMap.SimpleEntry<String, String>("AcademicStatus", Integer.toString(user.getAcademicStatus()));
        AbstractMap.SimpleEntry<String, String> pictureURL = new AbstractMap.SimpleEntry<String, String>("PictureURL", user.getPictureURL().getPath());

        JSONObject json = (JSONObject) postRequest(activity, "http://laddr.xyz/api/user",
                username, firstName, lastName, password, email,
                description, resume, academicStatus, pictureURL);

        Log.d("LADDER_DEBUG", json.toString());

        String result = json.getString("success");
        if (result.equals("true")) {
            return true;
        }

        return false;
    }

    /**
     * Adds an Organization to the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param organization The Organization object to add
     * @return Success as a boolean value.
     * @throws IOException
     * @throws JSONException
     */
    public static boolean addOrganization(Activity activity, Organization organization) throws IOException, JSONException {

        //TODO: Add validation

        AbstractMap.SimpleEntry<String, String> username = new AbstractMap.SimpleEntry<String, String>("Username", organization.getUsername());
        AbstractMap.SimpleEntry<String, String> orgName = new AbstractMap.SimpleEntry<String, String>("OrganizationName", organization.getOrganizationName());
        AbstractMap.SimpleEntry<String, String> password = new AbstractMap.SimpleEntry<String, String>("Password", organization.getPassword());
        AbstractMap.SimpleEntry<String, String> email = new AbstractMap.SimpleEntry<String, String>("Email", organization.getEmail());
        AbstractMap.SimpleEntry<String, String> address = new AbstractMap.SimpleEntry<String, String>("Address", organization.getAddress());
        AbstractMap.SimpleEntry<String, String> url;
        if (organization.getUrl() != null) {
            url = new AbstractMap.SimpleEntry<String, String>("URL", organization.getUrl().toString());
        } else {
            url = new AbstractMap.SimpleEntry<String, String>("URL", "");
        }
        AbstractMap.SimpleEntry<String, String> mission = new AbstractMap.SimpleEntry<String, String>("MissionStatement", organization.getMissionStatement());
        AbstractMap.SimpleEntry<String, String> pictureURL = new AbstractMap.SimpleEntry<String, String>("Picture", organization.getPictureURL().getPath());

        JSONObject json = (JSONObject)postRequest(activity, "http://laddr.xyz/api/organization",
                username, orgName, password, email,
                address, url, mission, pictureURL);

        Log.d("LADDER_DEBUG", json.toString());

        String result = json.getString("success");
        if (result.equals("true")) {
            return true;
        }

        return false;
    }

    /**
     * Adds a Posting object to the database.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param posting The posting to add to the database.
     * @return Success or failure of database operation.
     * @throws IOException
     * @throws JSONException
     */
    public static boolean addPosting(Activity activity, Posting posting) throws IOException, JSONException {

        //TODO: Add validation

        AbstractMap.SimpleEntry<String, String> postingID = new AbstractMap.SimpleEntry<String, String>("OrganizationID", posting.getOrganizerID());
        AbstractMap.SimpleEntry<String, String> jobTitle = new AbstractMap.SimpleEntry<String, String>("JobTitle", posting.getJobTitle());
        AbstractMap.SimpleEntry<String, String> location = new AbstractMap.SimpleEntry<String, String>("Location", posting.getLocation());
        AbstractMap.SimpleEntry<String, String> description = new AbstractMap.SimpleEntry<String, String>("Description", posting.getJobDescription());

        JSONObject json = (JSONObject)postRequest(activity, "http://laddr.xyz/api/posting", postingID, jobTitle, location, description);

        Log.d("LADDER_DEBUG", json.toString());

        String result = json.getString("success");
        if (result.equals("true")) {
            return true;
        }

        return false;
    }

    public static boolean applyPosting(Activity activity, Posting posting) throws IOException, JSONException{
        AbstractMap.SimpleEntry<String, String> postingID = new AbstractMap.SimpleEntry<String, String>("PostingID", posting.getPostingID());
        JSONObject json = (JSONObject)postRequest(activity, "http://laddr.xyz/api/apply", postingID);

        String result = json.getString("success");
        if (result.equals("true")) {
            return true;
        }

        return false;
    }


    /**
     * Logs in either a User or Organization.
     * @param activity  The calling activity, included to get reference to GlobalState
     * @param e The email of the user
     * @param p The password of the user.
     * @return A JSON Array containing the information about the logged in user.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject loginProfile(Activity activity, String e, String p) throws IOException, JSONException {

        AbstractMap.SimpleEntry<String, String> email = new AbstractMap.SimpleEntry<String, String>("Email", e);
        AbstractMap.SimpleEntry<String, String> password = new AbstractMap.SimpleEntry<String, String>("Password", p);

        JSONObject json = (JSONObject) postRequest(activity, "http://laddr.xyz/api/login", email, password);

        return json;
    }
}
