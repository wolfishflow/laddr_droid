package codebusters.laddr.modules.postings;

import org.androidannotations.annotations.EFragment;
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
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Posting;
import codebusters.laddr.utility.DividerItemDecoration;
import codebusters.laddr.utility.GetAllPostingsTask;

/**
 * Created by alansimon on 2016-10-15.
 */

@EFragment(R.layout.fragment_postings)
public class PostingsFragment extends Fragment {

    private final String TAG = "Postings Fragment";

    private static GlobalState globalState;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        globalState = (GlobalState) this.getActivity().getApplication();

        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Postings");

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_postings);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_postings);


        try {

            if (globalState.getToken() != null) {
                //get all the postings
                final ArrayList<Posting> allPostings = new GetAllPostingsTask(getActivity()).execute().get();
                mAdapter = new PostingsAdapter(allPostings);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);

                //Add a listener to each list object
                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Posting singlePosting = allPostings.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("posting", singlePosting);

                        Fragment fr = new PostingsContentFragment();
                        fr.setArguments(bundle);
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.frlt_fragment_container_home, fr);
                        ft.addToBackStack(null);
                        ft.commit();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(getActivity().getApplicationContext(), "Longos", Toast.LENGTH_SHORT).show();

                    }
                }));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}
