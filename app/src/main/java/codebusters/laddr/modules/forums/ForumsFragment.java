package codebusters.laddr.modules.forums;

import android.app.Fragment;
import android.support.v7.widget.Toolbar;

import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;

/**
 * Created by alansimon on 2016-11-14.
 */

public class ForumsFragment extends Fragment {

    private final String TAG = "Forums Fragment";
    private static GlobalState globalState;

    final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    //toolbar.setTitle("Forums");
}
