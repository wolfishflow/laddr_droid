package codebusters.laddr.data;

import android.app.Application;

/**
 * Created by greg on 10/09/16.
 * <p>
 * GlobalState will be a place to keep any global variables we need to keep track of. Think
 * AppDelegate, except in Android. Again, it's a Singleton so you get a reference to it by calling
 * (GlobalState) <someclass>.getApplication()
 */
public class GlobalState extends Application {

    private static GlobalState singleton;
    private String token;

    private SignUpUser signUpUserValue;
    private User userValue;

    public User getUserValue() {
        return userValue;
    }

    public void setUserValue(User userValue) {
        this.userValue = userValue;
    }


    public SignUpUser getSignUpUserValue() {
        return signUpUserValue;
    }

    public void setSignUpUserValue(SignUpUser signUpUserValue) {
        this.signUpUserValue = signUpUserValue;
    }

    /**
     * Sets the token used to authenticate API requests.
     *
     * @param token The token as a string.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Returns the token used to authenticate API requests.
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns an instance of the GlobalState class.
     *
     * @return
     */
    public static GlobalState getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
