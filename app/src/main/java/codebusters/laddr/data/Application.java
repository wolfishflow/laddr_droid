package codebusters.laddr.data;

/**
 * Created by greg on 01/12/16.
 */

public class Application {

    private String jobTitle;
    private String postingId;
    private String organizationName;
    private String organizationProfileId;
    private String organizationPhoto;
    private int status;
    private final String[] STATUSES = new String[] {
      "Pending", "Declined", "Approved", "Posting Cancelled"
    };

    public Application() {
    }

    public Application(String jobTitle, String postingId, String organizationName, String organizationProfileId, int status) {
        this.jobTitle = jobTitle;
        this.postingId = postingId;
        this.organizationName = organizationName;
        this.organizationProfileId = organizationProfileId;
        this.status = status;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationProfileId() {
        return organizationProfileId;
    }

    public void setOrganizationProfileId(String organizationProfileId) {
        this.organizationProfileId = organizationProfileId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        return STATUSES[status];
    }

    public String getOrganizationPhoto() {
        return organizationPhoto;
    }

    public void setOrganizationPhoto(String organizationPhoto) {
        this.organizationPhoto = organizationPhoto;
    }
}
