package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

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
    ProgressBar progressBar;

    private static GlobalState globalState;
    @BindView(R.id.tv_loginLogo)
    TextView tvLoginLogo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
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
        etPassword = (EditText) getActivity().findViewById(R.id.et_password);
        cbRemember = (CheckBox) getActivity().findViewById(R.id.cb_remember);
        etEmail.setText("alan@laddr.xyz");
        etPassword.setText("password");
    }

    /*
    Display progressbar when Clicked
    Get Global state.
    Check if the remember box is true, if so store email + pw in shared preferences
    Try LoginTask with email + pw text
    TODO: Should only move to the new activity if i can verify the success of logintask
    TODO: Add the shared pref pre-check if it's been fufilled post or pre view created.
     */

//    @Click(R.id.btn_woo)
//    void woo(){
//        MorphingButton btn = (MorphingButton) getActivity().findViewById(R.id.btn_woo);
//        Drawable abc = new IconicsDrawable(getActivity())
//                .icon(FontAwesome.Icon.faw_sign_in)
//                .color(Color.BLUE)
//                .sizeDp(24);
//
//        MorphingButton.Params circle = MorphingButton.Params.create()
//                .duration(500)
//                .cornerRadius(200) // 56 dp
//                .width(200) // 56 dp
//                .height(200) // 56 dp
//                .color((R.color.md_green_50)) // normal state color
//                .colorPressed((R.color.md_green_400)) // pressed state color
//                .icon(R.drawable.ic_goal); // icon
//        btn.morph(circle);
//    }

    @Click(R.id.btn_login)
    void loginClicked() {
        //Log.d(LOGIN_F_TAG, Boolean.toString(progressBar.isShown()));
        //progressBar.setVisibility(View.VISIBLE);

        globalState = (GlobalState) getActivity().getApplication();

        if (cbRemember.isChecked()) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.saved_email), etEmail.getText().toString());
            editor.putString(getString(R.string.saved_password), etPassword.getText().toString());
            editor.commit();

            String defaultValue = getResources().getString(R.string.saved_email);
            String emailValue = sharedPref.getString(getString(R.string.saved_email), defaultValue);
            defaultValue = getResources().getString(R.string.saved_password);
            String passwordValue = sharedPref.getString(getString(R.string.saved_password), defaultValue);
        }

        try {
            if (new LoginTask(getActivity()).execute(etEmail.getText().toString(), etPassword.getText().toString()).get()) {
                Intent intent = new Intent(getActivity(), HomeActivity_.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "Error Logging in", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*
    TODO: Create new fragment for our password recovery method
     */

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
        ft.replace(R.id.frlt_fragment_container_login, fr).addToBackStack("Login");
        ft.commit();
    }

}
