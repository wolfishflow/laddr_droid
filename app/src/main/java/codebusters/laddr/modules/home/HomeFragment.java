package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.modules.postings.PostingsActivity;

/**
 * Created by alansimon on 2016-09-18.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myToolbar = (Toolbar) getActivity().findViewById(R.id.my_toolbar);

        myToolbar.setTitle("Home");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));

        new DrawerBuilder().withActivity(getActivity()).build();

    }

    @Click(R.id.button4)
    void postingsClicked() {
        Intent intent = new Intent(getActivity(), PostingsActivity.class);
        startActivity(intent);
        //getActivity().finish();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
