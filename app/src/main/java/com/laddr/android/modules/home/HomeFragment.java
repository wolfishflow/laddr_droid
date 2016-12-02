package com.laddr.android.modules.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.laddr.android.R;
import com.laddr.android.data.Application;
import com.laddr.android.data.GlobalState;
import com.laddr.android.data.Posting;
import com.laddr.android.modules.postings.PostingsContentFragment;
import com.laddr.android.utility.DividerItemDecoration;
import com.laddr.android.utility.GetApplicationTask;
import com.laddr.android.utility.GetPostingTask;
import com.laddr.android.utility.UpdateFdiTask;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.roughike.bottombar.BottomBar;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        globalState = (GlobalState) getActivity().getApplication();
        myToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        myToolbar.setTitle("Home");


        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_applications);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_home);
        try {
            UpdateFdiTask uft = new UpdateFdiTask(getActivity(), FirebaseInstanceId.getInstance().getToken());
            if (uft.execute().get()){
                //Toast.makeText(globalState, "Successly updated", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(globalState, "Failure to update", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {

            if (globalState.getToken() != null) {
                //get all the postings
                final ArrayList<Application> allApplications = new GetApplicationTask(getActivity()).execute().get();
                mAdapter = new ApplicationsAdapter(allApplications);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);

                //Add a listener to each list object
                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener4(getActivity().getApplicationContext(), mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Application singleApplication = allApplications.get(position);

                        String singlePostingID = singleApplication.getPostingId();

                        Posting singlePosting = null;
                        try {
                            singlePosting = new GetPostingTask(getActivity()).execute(singlePostingID).get();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("posting", singlePosting);
                            Fragment fr = new PostingsContentFragment();
                            fr.setArguments(bundle);
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.frlt_fragment_container_home, fr);
                            ft.addToBackStack(null);
                            ft.commit();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
            }

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
