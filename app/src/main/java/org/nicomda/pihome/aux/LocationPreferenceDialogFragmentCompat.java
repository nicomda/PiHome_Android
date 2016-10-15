package org.nicomda.pihome.aux;

/**
 * Created by nicomda on 14/10/16.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.nicomda.pihome.R;

import static android.content.ContentValues.TAG;

public class LocationPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private EditText geocoder;
    private ImageView geocoder_button;
    private String locString;
    private GoogleMap map;
    private SupportMapFragment mapFragment;

    public static LocationPreferenceDialogFragmentCompat newInstance(String key) {
        final LocationPreferenceDialogFragmentCompat
                fragment = new LocationPreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        geocoder = (EditText) view.findViewById(R.id.edittext_geocoder);
        geocoder_button = (ImageView) view.findViewById(R.id.button_search_geocoder);
        geocoder.setText("HOLA CARACOLA");
        DialogPreference preference = getPreference();
        if (preference instanceof LocationPreference) {
            locString = ((LocationPreference) preference).getLoc_string();
        }
        //TODO JUST FOR TESTING THIS VALUE ASSIGNMENT
        locString = "HOLA";
        mapFragment = ((SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                }
            }
        });
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            DialogPreference preference = getPreference();
            LocationPreference locpref = ((LocationPreference) preference);
            locpref.setLocationString(locString);

        }
    }


}
