package codebusters.laddr.modules.forums;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.Comment;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Topic;
import codebusters.laddr.utility.ApplyPosting;
import codebusters.laddr.utility.DividerItemDecoration;
import codebusters.laddr.utility.GetAllCommentsTask;
import codebusters.laddr.utility.GetAllTopicsTask;

/**
 * Created by alansimon on 2016-11-26.
 */

public class ForumsContentFragment extends Fragment {

    private final String TAG = "Comments CONTENT";
    private static GlobalState globalState;
    private Comment singleComment;
    private Topic singleTopic;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_forums_content, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        singleTopic = bundle.getParcelable("topic");


        globalState = (GlobalState) this.getActivity().getApplication();
        mRecyclerView = (RecyclerView) this.getActivity().findViewById(R.id.rv_forums_content);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {

            if (globalState.getToken() != null) {
                //get all the postings
                final ArrayList<Comment> allComments = new GetAllCommentsTask(getActivity()).execute(singleTopic.getTopicId()).get();
                mAdapter = new CommentsAdapter(allComments);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);

//                //Add a listener to each list object
//                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener2(getActivity().getApplicationContext(), mRecyclerView, new ClickListener() {
//                    @Override
//                    public void onClick(View view, int position) {
//
//                        Topic singleCommnet = allComments.get(position);
//
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable("topic", singleTopic);
//
//                        Fragment fr = new ForumsContentFragment();
//                        fr.setArguments(bundle);
//                        FragmentManager fm = getFragmentManager();
//                        FragmentTransaction ft = fm.beginTransaction();
//                        ft.replace(R.id.frlt_fragment_container_home, fr);
//                        ft.addToBackStack(null);
//                        ft.commit();
//                    }
//
//                    @Override
//                    public void onLongClick(View view, int position) {
//                        Toast.makeText(getActivity().getApplicationContext(), "Longos", Toast.LENGTH_SHORT).show();
//
//                    }
//                }));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }









        /*
        Assign Button, initialize it, and assign a listener to it.
         */



//        Button button = (Button) getActivity().findViewById(R.id.btn_postingApply);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                try {
//                    Toast.makeText(getActivity(), "sending posting", Toast.LENGTH_SHORT).show();
//                    new ApplyPosting(getActivity()).execute(singleTopic).get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getActivity(), "failed posting", Toast.LENGTH_SHORT).show();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getActivity(), "failed posting", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getActivity(), "success posting", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
