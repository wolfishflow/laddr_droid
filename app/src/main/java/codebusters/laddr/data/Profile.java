package codebusters.laddr.data;

import java.net.URL;
import java.util.Date;

/**
 * Created by greg on 5/3/2016.
 *
 * A data class representing a profile. Profile is the superclass of both Users, who can apply for jobs,
 * and Organizations, who can post jobs and review applicants.
 */
public abstract class Profile {
    protected String profileID;
    protected String username;
    protected String email;
    protected URL pictureURL;
    protected Date timestamp;
    protected int accountType;
    protected String password;
    protected String picLink;

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URL getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(URL pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
