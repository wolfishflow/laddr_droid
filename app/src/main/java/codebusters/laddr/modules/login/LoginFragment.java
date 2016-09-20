package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.modules.home.HomeActivity_;
import codebusters.laddr.utility.LoginTask;

/**
 * Created by alansimon on 2016-09-17.
 */

@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {

    private static final String LOGIN_F_TAG = "LOGIN_FRAGMENT";

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.progressBar)
    private ProgressBar progressBar;

    private static GlobalState globalState;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        etEmail = (EditText) getActivity().findViewById(R.id.et_email);
        etPassword = (EditText) getActivity().findViewById(R.id.et_password);
        cbRemember = (CheckBox) getActivity().findViewById(R.id.cb_remember);
        etEmail.setText("NewestUser");
        etPassword.setText("password");
    }

    /*
    TODO: Need to run the proper login validation. and save the JWT. mebe to shared preferences???
     */

    @Click(R.id.btn_login)
    void loginClicked() {
        //Log.d(LOGIN_F_TAG, Boolean.toString(progressBar.isShown()));
        progressBar.setVisibility(View.VISIBLE);

        globalState = (GlobalState) getActivity().getApplication();

        if (cbRemember.isChecked())
        {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.saved_email), etEmail.getText().toString());
            editor.putString(getString(R.string.saved_password), etPassword.getText().toString());
            editor.commit();

            String defaultValue = getResources().getString(R.string.saved_email);
            String emailValue = sharedPref.getString(getString(R.string.saved_email),defaultValue);
            defaultValue = getResources().getString(R.string.saved_password);
            String passwordValue = sharedPref.getString(getString(R.string.saved_password),defaultValue);
    }
        
        try {
            new LoginTask(getActivity()).execute(etEmail.getText().toString(), etPassword.getText().toString()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getActivity(), HomeActivity_.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Click(R.id.btn_forgot_password)
    void forgotClicked() {
        Toast.makeText(getActivity(), "forgot pwd", Toast.LENGTH_SHORT).show();
    }

    /*
        Create new Fragment of X type
        Use Fragment Manager & Fragment Transactions to facilitate a replacement
        Switch current fragment into the declared placeholder
        Include the backstack, as pressing back will kill the app.
     */

    @Click(R.id.btn_signup)
    void signupClicked() {
        Fragment fr = new SignUpFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.login_fragment_container, fr).addToBackStack("Login");
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
