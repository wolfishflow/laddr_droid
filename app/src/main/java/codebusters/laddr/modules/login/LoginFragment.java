package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import codebusters.laddr.modules.home.HomeActivity;
import codebusters.laddr.modules.home.HomeActivity_;
import codebusters.laddr.modules.postings.PostingsActivity;
import codebusters.laddr.R;

/**
 * Created by alansimon on 2016-09-17.
 */

@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {

    private static final String LOGIN_F_TAG = "LOGIN_FRAGMENT";

    @BindView(R.id.progressBar)
    private ProgressBar progressBar;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
    }

    /*
    TODO: Need to run the proper login validation. and save the JWT. mebe to shared preferences???
     */

    @Click(R.id.btn_login)
    void loginClicked() {
        //Log.d(LOGIN_F_TAG, Boolean.toString(progressBar.isShown()));
        progressBar.setVisibility(View.VISIBLE);
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

}
