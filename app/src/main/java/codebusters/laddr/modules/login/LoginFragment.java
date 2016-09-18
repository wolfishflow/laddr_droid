package codebusters.laddr.modules.login;

import android.app.Fragment;
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


    @Click(R.id.btn_login)
    void loginClicked() {
        Toast.makeText(getActivity(), "yum", Toast.LENGTH_SHORT).show();
        Intent intent  =  new Intent(getActivity(), PostingsActivity.class);
        startActivity(intent);

    }

    @Click(R.id.btn_forgot_password)
    void forgotClicked() {
        Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();

    }

    @Click(R.id.btn_signup)
    void signupClicked() {
        Toast.makeText(getActivity(), "hah", Toast.LENGTH_SHORT).show();

    }

}
