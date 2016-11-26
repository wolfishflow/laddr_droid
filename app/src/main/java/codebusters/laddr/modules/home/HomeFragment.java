package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.roughike.bottombar.BottomBar;

import org.androidannotations.annotations.EFragment;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.utility.UpdateFdiTask;

/**
 * Created by alansimon on 2016-09-18.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar myToolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private static GlobalState globalState;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        globalState = (GlobalState) getActivity().getApplication();
        myToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        myToolbar.setTitle("Home");
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_home);
        Log.d("welp", FirebaseInstanceId.getInstance().getToken().toString());

        try {
            UpdateFdiTask uft = new UpdateFdiTask(getActivity(), FirebaseInstanceId.getInstance().getToken());
            if (uft.execute().get()){
                Toast.makeText(globalState, "Successly updated", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(globalState, "Failure to update", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
