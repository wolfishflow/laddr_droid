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
import codebusters.laddr.data.User;
import codebusters.laddr.utility.GetUserTask;

/**
 * Created by alansimon on 2016-10-03.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends Fragment {

    private static GlobalState globalState;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    @AfterViews
    void setVars() {
        tvName = (TextView) getActivity().findViewById(R.id.tv_name);
        tvDescription = (TextView) getActivity().findViewById(R.id.tv_description);

        globalState = (GlobalState) getActivity().getApplication();

        try {
            User user;
            new GetUserTask(getActivity()).execute(globalState.getUserValue().getProfileID());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
