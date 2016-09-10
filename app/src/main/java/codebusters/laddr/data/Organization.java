package codebusters.laddr.data;
import java.net.URL;
import java.util.Date;

/**
 * Created by greg on 5/3/2016.
 *
 * Data class representing an Organization user.
 */
public class Organization extends Profile {

    protected String organizationName;
    protected String address;
    protected URL url;
    protected String missionStatement;

    /**
     * No-arg constructor.
     */
    public Organization() {

    }

    /**
     * Constructor that sets all Organization fields.
     * @param profileID The UUID of the organization.
     * @param username The username of the organization.
     * @param email Contact email
     * @param pictureURL The organization's avatar
     * @param timestamp When the profile was created
     * @param organizationName Name of the organization
     * @param address Address of the organization
     * @param url Public website of the organization
     * @param missionStatement Mission statement, or further info about the organization.
     */
    public Organization(String profileID, String username, String email, URL pictureURL, Date timestamp,
                        String organizationName, String address, URL url, String missionStatement) {
        //fields from Profile superclass
        this.profileID = profileID;
        this.username = username;
        this.email = email;
        this.pictureURL = pictureURL;
        this.timestamp = timestamp;

        //organization fields
        this.organizationName = organizationName;
        this.address = address;
        this.url = url;
        this.missionStatement = missionStatement;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }
}
