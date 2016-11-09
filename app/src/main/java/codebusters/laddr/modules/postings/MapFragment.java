package codebusters.laddr.modules.postings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import codebusters.laddr.R;

/**
 * Created by alansimon on 2016-11-07.
 */
//@EFragment(R.layout.fragment_posting_map)
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

//    @AfterViews
//    void setup(){
//
//        MapFragment mapFragment = (MapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);
//
//        //mapFragment.getMapAsync(this);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posting_map,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        //mapFragment.getMapAsync();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng coordinate = new LatLng(21.182782, 72.830115);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 11);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        googleMap.animateCamera(yourLocation);
    }
}
