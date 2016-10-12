package codebusters.laddr.modules.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;

/**
 * Created by alansimon on 2016-10-03.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends Fragment {

    public final String TAG = "ProfileFragment";

    private static GlobalState globalState;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_academicStatus)
    TextView tvAcademicStatus;

    @AfterViews
    void setVars() {
        tvName = (TextView) getActivity().findViewById(R.id.tv_name);
        tvDescription = (TextView) getActivity().findViewById(R.id.tv_description);
        tvEmail = (TextView) getActivity().findViewById(R.id.tv_email);
        tvAcademicStatus = (TextView) getActivity().findViewById(R.id.tv_academicStatus);


//        String profileId = globalState.getUserValue().getProfileID();
//
//        try {
//            new GetUserTask(getActivity()).execute(profileId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        globalState = (GlobalState) getActivity().getApplication();
        //Log.d(TAG, "onViewCreated: " + globalState.getUserValue().getFirstName());
        tvName.setText(globalState.getUserValue().getFirstName() + " " + globalState.getUserValue().getLastName());


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
