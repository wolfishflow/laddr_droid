package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.SignUpUser;

/**
 * Created by alansimon on 2016-09-28.
 */
@EFragment(R.layout.fragment_userdetails)
public class UserDetailsFragment extends Fragment {

    private static GlobalState globalState;
    @BindView(R.id.et_firstname)
    EditText etFirstname;
    @BindView(R.id.et_lastname)
    EditText etLastname;
    @BindView(R.id.spr_academicstatus)
    Spinner spinner;
    @BindView(R.id.til_firstname)
    TextInputLayout tilFirstname;
    @BindView(R.id.til_lastname)
    TextInputLayout tilLastname;

    @AfterViews
    void setVars() {
        etFirstname = (EditText) getActivity().findViewById(R.id.et_firstname);
        etLastname = (EditText) getActivity().findViewById(R.id.et_lastname);
        spinner = (Spinner) getActivity().findViewById(R.id.spr_academicstatus);
        tilFirstname = (TextInputLayout) getActivity().findViewById(R.id.til_firstname);
        tilLastname = (TextInputLayout) getActivity().findViewById(R.id.til_lastname);

    }

    @Click(R.id.btn_signup_add_user)
    void signUpUser() {

        String firstNameValue = etFirstname.getText().toString();
        String lastNameValue = etLastname.getText().toString();
        String spinnerValue = spinner.getSelectedItem().toString();

        globalState = (GlobalState) getActivity().getApplication();
        SignUpUser su = globalState.getSignUpUserValue();

        if (firstNameValue.length() == 0) {
            etFirstname.setError("Email Required!");
        }

            if (spinnerValue.equals("High School")) {
                Log.d("abc", "HS");
            } else if (spinnerValue.equals("College")) {
                Log.d("abc", "C");
            } else if (spinnerValue.equals("University")) {
                Log.d("abc", "U");
            } else if (spinnerValue.equals("Graduated")) {
                Log.d("abc", "G");
            } else if (spinnerValue.equals("Not In School")) {
                Log.d("abc", "N");
            }

            su.setFirstName(firstNameValue);
            su.setLastName(lastNameValue);


        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){
            // TODO: inflate a fragment view
            View rootView = super.onCreateView(inflater, container, savedInstanceState);
            ButterKnife.bind(this, rootView);
            return rootView;
        }
    }
