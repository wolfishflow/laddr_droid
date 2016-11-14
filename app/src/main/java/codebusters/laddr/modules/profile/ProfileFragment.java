package codebusters.laddr.modules.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.roughike.bottombar.BottomBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.modules.postings.PostingsActivity;

/**
 * Created by alansimon on 2016-10-03.
 */
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends Fragment {

    public final String TAG = "ProfileFragment";

    private static GlobalState globalState;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_academicStatus)
    TextView tvAcademicStatus;
    @BindView(R.id.toolbar)
    Toolbar myToolbar;

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
        tvDescription.setText(globalState.getUserValue().getUserDescription());
        tvEmail.setText(globalState.getUserValue().getEmail());
//        tvAcademicStatus.setText(globalState.getUserValue().getAcademicStatus());
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_profile);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        myToolbar.setTitle("Profile");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
