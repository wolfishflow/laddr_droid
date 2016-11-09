package codebusters.laddr.modules.postings;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
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
//@EFragment(R.layout.fragment_postings_content)
public class PostingsContentFragment extends Fragment implements OnMapReadyCallback {
    //@BindView(R.id.tv_postingTitle)
    TextView tvPostingTitle;
    //@BindView(R.id.tv_postingOrganizationName)
    TextView tvPostingOrganizationName;
    //@BindView(R.id.tv_postingLocation)
    TextView tvPostingLocation;
    //@BindView(R.id.tv_postingsDescription)
    TextView tvPostingsDescription;
    //@BindView(R.id.tv_postingTimeStamp)
    TextView tvPostingTimeStamp;
    //@BindView(R.id.btn_postingApply)
    Button btnPostingApply;


    Posting singlePosting;
    private FragmentActivity myContext;
    private MapView mapView;
    private GoogleMap googleMap;

//    @AfterViews
//    void stuff()
//    {
//
//    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    private final String TAG = "POSTINGS CONTENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_postings_content, container, false);
//        Bundle bundle = this.getArguments();
//        singlePosting = bundle.getParcelable("posting");
//
//        TextView tvPostingTitle = (TextView) getActivity().findViewById(R.id.tv_postingTitle);
//        TextView tvPostingOrganizationName = (TextView) getActivity().findViewById(R.id.tv_postingOrganizationName);
//        TextView tvPostingLocation = (TextView) getActivity().findViewById(R.id.tv_postingLocation);
//        TextView tvPostingsDescription = (TextView) getActivity().findViewById(R.id.tv_postingsDescription);
//        TextView tvPostingTimeStamp = (TextView) getActivity().findViewById(R.id.tv_postingTimeStamp);
//
//        tvPostingTitle.setText(singlePosting.getJobTitle());
//        tvPostingOrganizationName.setText(singlePosting.getOrganizerName());
//        tvPostingLocation.setText(singlePosting.getLocation());
//        tvPostingsDescription.setText(singlePosting.getJobDescription());
//
//        Log.d(TAG, singlePosting.getLatitude().toString() + "||||" + singlePosting.getLongitude().toString());

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

        Log.d(TAG, singlePosting.getLatitude().toString() + "||||" + singlePosting.getLongitude().toString());

        //tvPostingTimeStamp.setText(singlePosting);



//        Fragment mapFragment = new MapFragment();
//        FragmentTransaction tr = getChildFragmentManager().beginTransaction();
//        tr.add(R.id.map_holder, mapFragment).commit();

        mapView = (MapView) getActivity().findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
//
//
        mapView.getMapAsync(this);
        //googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        //googleMap.setMyLocationEnabled(true);

//        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

//    @Click(R.id.btn_postingApply)
//     void applyToPosting(){
//        try {
//            Toast.makeText(getActivity(), "sending posting", Toast.LENGTH_SHORT).show();
//            new ApplyPosting(getActivity()).execute(singlePosting).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        Toast.makeText(getActivity(), "success posting", Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        MapsInitializer.initialize(this.getActivity());
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng coordinate = new LatLng(21.182782, 72.830115);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 11);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        googleMap.animateCamera(yourLocation);
        mapQuest();
        //googleMap.setMyLocationEnabled(true);

    }
    

    public void mapQuest(){

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
