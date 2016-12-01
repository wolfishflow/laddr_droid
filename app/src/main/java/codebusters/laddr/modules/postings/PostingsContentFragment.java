package codebusters.laddr.modules.postings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.Posting;
import codebusters.laddr.utility.ApplyPosting;

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
    private Toolbar toolbar;
    final static long MILLIS_PER_DAY = 24 * 3600 * 1000;



    private final String TAG = "POSTINGS CONTENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_postings_content, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        singlePosting = bundle.getParcelable("posting");
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                getActivity().onBackPressed();
            }
        });


        TextView tvPostingTitle = (TextView) getActivity().findViewById(R.id.tv_postingTitle);
        TextView tvPostingOrganizationName = (TextView) getActivity().findViewById(R.id.tv_postingOrganizationName);
        TextView tvPostingLocation = (TextView) getActivity().findViewById(R.id.tv_postingLocation);
        TextView tvPostingsDescription = (TextView) getActivity().findViewById(R.id.tv_postingsDescription);
        TextView tvPostingTimeStamp = (TextView) getActivity().findViewById(R.id.tv_postingTimeStamp);

        tvPostingTitle.setText(singlePosting.getJobTitle());
        tvPostingOrganizationName.setText(singlePosting.getOrganizerName());
        tvPostingLocation.setText(singlePosting.getLocation());
        tvPostingsDescription.setText(singlePosting.getJobDescription());

        tvPostingTimeStamp.setText(deadlineCalc(singlePosting.getDeadline(), singlePosting.getEventDate()));

        /*
        Assign MapView, enable it and initialize it.
         */
        mapView = (MapView) getActivity().findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        /*
        Assign Button, initialize it, and assign a listener to it.
         */
        Button button = (Button) getActivity().findViewById(R.id.btn_postingApply);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getActivity(), "sending posting", Toast.LENGTH_SHORT).show();
                    new ApplyPosting(getActivity()).execute(singlePosting).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "failed posting", Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "failed posting", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "success posting", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public String deadlineCalc(String deadline, String event){
        String dateString = "Due Today!";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        Calendar dateToday = Calendar.getInstance();
        Calendar deadlineDate = Calendar.getInstance();
        Calendar dateEvent = Calendar.getInstance();



        try {
            deadlineDate.setTime(format.parse(deadline));
            dateEvent.setTime(format.parse(event));


            long msDiff = deadlineDate.getTimeInMillis() - dateToday.getTimeInMillis();
            long daysDiff = Math.round(msDiff / ((double)MILLIS_PER_DAY));

            if (daysDiff == 0){
                return dateString;
            } else {
                return  String.valueOf(daysDiff)+" days to apply!";
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        MapsInitializer.initialize(this.getActivity());
        mapQuest();
    }

    public void mapQuest() {
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng coordinate = new LatLng(singlePosting.getLatitude(), singlePosting.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(singlePosting.getLatitude(), singlePosting.getLongitude()))
                .title(singlePosting.getLocation()));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 16);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        googleMap.animateCamera(yourLocation);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
