package codebusters.laddr.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by greg on 5/3/2016.
 * <p>
 * A data class representing a volunteer job posting
 */
public class Posting implements Parcelable {
    private String postingID;
    private String jobTitle;
    private String organizerID;
    private String organizerName;
    private String location;
    private String jobDescription;
    private Double latitude;
    private Double longitude;
    private String pictureLink;

    public Posting() {

    }

    public Posting(String postingID, String jobTitle, String organizerName, String location, String jobDescription, Double latitude, Double longitude) {
        this.postingID = postingID;
        this.jobTitle = jobTitle;
        this.organizerName = organizerName;
        this.location = location;
        this.jobDescription = jobDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Posting(Parcel in) {
        postingID = in.readString();
        jobTitle = in.readString();
        organizerID = in.readString();
        organizerName = in.readString();
        location = in.readString();
        jobDescription = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();

    }

    public static final Creator<Posting> CREATOR = new Creator<Posting>() {
        @Override
        public Posting createFromParcel(Parcel in) {
            return new Posting(in);
        }

        @Override
        public Posting[] newArray(int size) {
            return new Posting[size];
        }
    };

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postingID);
        dest.writeString(jobTitle);
        dest.writeString(organizerID);
        dest.writeString(organizerName);
        dest.writeString(location);
        dest.writeString(jobDescription);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
