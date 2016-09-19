package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import codebusters.laddr.PostingsActivity;
import codebusters.laddr.R;

/**
 * Created by alansimon on 2016-09-17.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment{

    private static final String LOGIN_F_TAG = "LOGIN_FRAGMENT";


    @Click(R.id.btn_login)
    void loginClicked() {
        Intent intent  =  new Intent(getActivity(), PostingsActivity.class);
        startActivity(intent);

    }

    @Click(R.id.btn_forgot_password)
    void forgotClicked() {
        Toast.makeText(getActivity(), "forgot pwd", Toast.LENGTH_SHORT).show();


    }

    @Click(R.id.btn_signup)
    void signupClicked() {
        Fragment fr = new SignUpFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_fragment_holder, fr).addToBackStack("Login");
        ft.commit();
    }

}
