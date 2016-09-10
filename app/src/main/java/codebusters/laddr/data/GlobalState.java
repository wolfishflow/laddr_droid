package codebusters.laddr.data;

import android.app.Application;

/**
 * Created by greg on 10/09/16.
 */
public class GlobalState extends Application {

    private static GlobalState singleton;
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    public static GlobalState getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
