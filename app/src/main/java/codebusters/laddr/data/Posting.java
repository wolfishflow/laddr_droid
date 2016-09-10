package codebusters.laddr.data;

/**
 * Created by greg on 5/3/2016.
 */
public class Posting {
    private String postingID;
    private String jobTitle;
    private String organizerID;
    private String organizerName;
    private String location;
    private String jobDescription;

    public Posting() {

    }

    public Posting(String postingID, String jobTitle, String organizerName, String location, String jobDescription) {
        this.postingID = postingID;
        this.jobTitle = jobTitle;
        this.organizerName = organizerName;
        this.location = location;
        this.jobDescription = jobDescription;
    }

    public String getPostingID() {
        return postingID;
    }

    public void setPostingID(String postingID) {
        this.postingID = postingID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(String organizerID) {
        this.organizerID = organizerID;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String toString() {
        return this.getJobTitle();
    }
}
