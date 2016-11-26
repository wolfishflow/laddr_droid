package codebusters.laddr.modules.forums;

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

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Topic;
import codebusters.laddr.utility.DividerItemDecoration;
import codebusters.laddr.utility.GetAllTopicsTask;

/**
 * Created by alansimon on 2016-11-14.
 */

@EFragment(R.layout.fragment_forums)
public class ForumsFragment extends Fragment {

    private final String TAG = "Forums Fragment";
    private static GlobalState globalState;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Forums");

        globalState = (GlobalState) this.getActivity().getApplication();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_forums);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {

            if (globalState.getToken() != null) {
                //get all the postings
                final ArrayList<Topic> allTopics = new GetAllTopicsTask(getActivity()).execute().get();
                mAdapter = new TopicsAdapter(allTopics);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);

                //Add a listener to each list object
                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener2(getActivity().getApplicationContext(), mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Topic singleTopic = allTopics.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("topic", singleTopic);

                        Fragment fr = new ForumsContentFragment();
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
