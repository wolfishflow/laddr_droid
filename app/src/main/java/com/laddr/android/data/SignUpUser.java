package com.laddr.android.data;


/**
 * Created by alansimon on 2016-09-28.
 */

public class SignUpUser {
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected int academicStatus;

    public SignUpUser(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(int academicStatus) {
        this.academicStatus = academicStatus;
    }
}
