package com.laddr.android.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laddr.android.R;

import org.androidannotations.annotations.EActivity;



@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_A_TAG = "LOGIN_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fr = new LoginFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frlt_fragment_container_login, fr);
        ft.commit();

    }

}
