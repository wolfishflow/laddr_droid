package codebusters.laddr.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alansimon on 2016-11-26.
 */


public class Topic implements Parcelable {

    private String topicId;
    private String title;
    private String profileId;
    private String timestamp;
    private String firstName;

    public Topic(String topicId, String title, String profileId, String timestamp, String firstName) {
        this.topicId = topicId;
        this.title = title;
        this.profileId = profileId;
        this.timestamp = timestamp;
        this.firstName = firstName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Topic() {

    }

    protected Topic(Parcel in) {
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
