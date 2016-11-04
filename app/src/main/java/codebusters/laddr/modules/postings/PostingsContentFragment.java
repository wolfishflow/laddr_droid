package codebusters.laddr.modules.postings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.Posting;
import codebusters.laddr.utility.ApplyPosting;
import codebusters.laddr.utility.GetAllPostingsTask;

/**
 * Created by alansimon on 2016-10-15.
 */
@EFragment(R.layout.fragment_postings_content)
public class PostingsContentFragment extends Fragment {
    @BindView(R.id.tv_postingTitle)
    TextView tvPostingTitle;
    @BindView(R.id.tv_postingOrganizationName)
    TextView tvPostingOrganizationName;
    @BindView(R.id.tv_postingLocation)
    TextView tvPostingLocation;
    @BindView(R.id.tv_postingsDescription)
    TextView tvPostingsDescription;
    @BindView(R.id.tv_postingTimeStamp)
    TextView tvPostingTimeStamp;
    @BindView(R.id.btn_postingApply)
    Button btnPostingApply;

    Posting singlePosting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        singlePosting = bundle.getParcelable("posting");

        TextView tvPostingTitle = (TextView) getActivity().findViewById(R.id.tv_postingTitle);
        TextView tvPostingOrganizationName = (TextView) getActivity().findViewById(R.id.tv_postingOrganizationName);
        TextView tvPostingLocation = (TextView) getActivity().findViewById(R.id.tv_postingLocation);
        TextView tvPostingsDescription = (TextView) getActivity().findViewById(R.id.tv_postingsDescription);
        TextView tvPostingTimeStamp = (TextView) getActivity().findViewById(R.id.tv_postingTimeStamp);

        tvPostingTitle.setText(singlePosting.getJobTitle());
        tvPostingOrganizationName.setText(singlePosting.getOrganizerName());
        tvPostingLocation.setText(singlePosting.getLocation());
        tvPostingsDescription.setText(singlePosting.getJobDescription());
        //tvPostingTimeStamp.setText(singlePosting);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

    @Click(R.id.btn_postingApply)
     void applyToPosting(){
        try {
            Toast.makeText(getActivity(), "sending posting", Toast.LENGTH_SHORT).show();
            new ApplyPosting(getActivity()).execute(singlePosting).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), "success posting", Toast.LENGTH_SHORT).show();

    }
}
