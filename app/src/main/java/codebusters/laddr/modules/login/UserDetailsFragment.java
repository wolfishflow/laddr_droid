package codebusters.laddr.modules.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.SignUpUser;
import codebusters.laddr.modules.home.HomeActivity_;
import codebusters.laddr.utility.AddUserTask;
import codebusters.laddr.utility.LoginTask;

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
            tilFirstname.setError("First Name Required!");
        } else if (lastNameValue.length() == 0) {
            tilLastname.setError("Last Name Required!");
        }

        if (spinnerValue.equals("High School")) {
            su.setAcademicStatus(1);
        } else if (spinnerValue.equals("College")) {
            su.setAcademicStatus(2);
        } else if (spinnerValue.equals("University")) {
            su.setAcademicStatus(3);
        } else if (spinnerValue.equals("Graduated")) {
            su.setAcademicStatus(4);
        } else if (spinnerValue.equals("Not Applicable")) {
            su.setAcademicStatus(0);
        }

        su.setFirstName(firstNameValue);
        su.setLastName(lastNameValue);

        // Now put add user task here
        try {
            new AddUserTask(getActivity()).execute(su).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Then login
        try {
            if (new LoginTask(getActivity()).execute(su.getEmail(), su.getPassword()).get()) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
