package com.laddr.android.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.laddr.android.R;
import com.laddr.android.data.GlobalState;
import com.laddr.android.data.SignUpUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alansimon on 2016-09-18.
 */
@EFragment(R.layout.fragment_signup)
public class SignUpFragment extends Fragment {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private static GlobalState globalState;

    private static final String SIGNUP_F_TAG = "SIGNUP_FRAGMENT";
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirmpassword)
    EditText etConfirmpassword;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_confirmpassword)
    TextInputLayout tilConfirmpassword;
    @BindView(R.id.tv_loginLogo)
    TextView tvLoginLogo;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    @Click(R.id.btn_next)
    void nextClicked() {
        String emailValue = etEmail.getText().toString();
        String passwordValue = etPassword.getText().toString();
        String confirmPasswordValue = etConfirmpassword.getText().toString();

        if (emailValue.length() == 0) {
            tilEmail.setError("Email Required!");
        } else if (!validateEmail(emailValue)) {
            tilEmail.setError("Email Format InCorrect!");
        } else if (passwordValue.length() == 0) {
            tilPassword.setError("Password Required!");
        }    else if (!validatePassword(passwordValue)) {
                tilPassword.setError("Password length must greater than 5!");
        } else if (confirmPasswordValue.length() == 0) {
            tilConfirmpassword.setError("Confirmation Password Required!");
        } else if (!confirmPasswordValue.equals(passwordValue)) {
            tilConfirmpassword.setError("Passwords Must Match!");
        } else {

            SignUpUser su = new SignUpUser();
            su.setEmail(emailValue);
            su.setPassword(passwordValue);

            globalState = (GlobalState) getActivity().getApplication();
            globalState.setSignUpUserValue(su);

            Fragment fr = new UserDetailsFragment_();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frlt_fragment_container_login, fr).addToBackStack("SignUp");
            ft.commit();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        String fontPath = "fonts/lobster1point4.otf";
        // Loading Font Face
        Typeface m_typeFace = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        tvLoginLogo = (TextView) view.findViewById(R.id.tv_loginLogo);
        // Applying font
        tvLoginLogo.setTypeface(m_typeFace);

        etEmail = (EditText) getActivity().findViewById(R.id.et_email);
        //etEmail.requestFocus(1);
        etPassword = (EditText) getActivity().findViewById(R.id.et_password);
        etConfirmpassword = (EditText) getActivity().findViewById(R.id.et_confirmpassword);
        tilEmail = (TextInputLayout) getActivity().findViewById(R.id.til_email);
        tilPassword = (TextInputLayout) getActivity().findViewById(R.id.til_password);
        tilConfirmpassword = (TextInputLayout) getActivity().findViewById(R.id.til_confirmpassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
