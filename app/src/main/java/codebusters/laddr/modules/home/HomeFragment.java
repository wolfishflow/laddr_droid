package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import codebusters.laddr.R;
import codebusters.laddr.modules.login.SignUpFragment_;
import codebusters.laddr.modules.postings.PostingsActivity;

/**
 * Created by alansimon on 2016-09-18.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment{


    @Click(R.id.button4)
    void postingsClicked() {
        Intent intent = new Intent(getActivity(), PostingsActivity.class);
        startActivity(intent);
        //getActivity().finish();
    }


}
