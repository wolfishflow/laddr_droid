package com.laddr.android.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alansimon on 2016-11-26.
 */

public class Comment implements Parcelable {

    private String commentId;
    private String profileId;
    private String timeStamp;
    private String topicId;
    private String body;
    private String pictureURL;
    private String firstName;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Comment() {

    }

    protected Comment(Parcel in) {
        commentId = in.readString();
        profileId = in.readString();
        timeStamp = in.readString();
        topicId = in.readString();
        body = in.readString();
        pictureURL = in.readString();
        firstName = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commentId);
        dest.writeString(profileId);
        dest.writeString(timeStamp);
        dest.writeString(topicId);
        dest.writeString(body);
        dest.writeString(pictureURL);
        dest.writeString(firstName);
    }
}
