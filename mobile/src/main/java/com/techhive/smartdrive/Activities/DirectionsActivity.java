package com.techhive.smartdrive.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.techhive.smartdrive.R;
import com.techhive.smartdrive.Utilities.Utils;
import com.techhive.smartdrive.model.LocationDirections;

import java.util.ArrayList;

public class DirectionsActivity extends AppCompatActivity {

    private AutoCompleteTextView from;
    private AutoCompleteTextView to;
    private Button loadDirections;

    private static final int REQ_CODE_SOURCE = 511;
    private static final int REQ_CODE_DEST = 512;
    private static final String TAG = "TestingCode";

    Place source, destination;
    Intent intent;
    LocationDirections fromLoc, toLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        from = (AutoCompleteTextView) findViewById(R.id.from);
        to = (AutoCompleteTextView) findViewById(R.id.to);
        loadDirections = (Button) findViewById(R.id.load_directions);

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Utils.hideSoftKeyboard(DirectionsActivity.this);
                launchSearchIntent(511);
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Utils.hideSoftKeyboard(DirectionsActivity.this);
                launchSearchIntent(512);
            }
        });


        intent = new Intent(this, NavActivity.class);

        loadDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<LocationDirections> directions = new ArrayList<>();

                Intent data = new Intent();
                directions.add(fromLoc);
                directions.add(toLoc);

                if(fromLoc!=null && toLoc!=null){
                    data.putExtra("fromLat",fromLoc.getLatLng().latitude);
                    data.putExtra("fromLong",fromLoc.getLatLng().longitude);
                    data.putExtra("toLat",toLoc.getLatLng().latitude);
                    data.putExtra("toLong",toLoc.getLatLng().longitude);
                    setResult(RESULT_OK, data);
                    finish();
                }else{
                    Toast.makeText(DirectionsActivity.this,"Please enter the location.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void launchSearchIntent(int code) {
        try {

            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setCountry("IND")
                    .build();

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(typeFilter)
                    .build(this);

            startActivityForResult(intent, code);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 511) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                source = place;
                fromLoc = new LocationDirections();
                fromLoc.setName((String) place.getName());
                fromLoc.setAddress((String) place.getAddress());
                fromLoc.setLatLng(place.getLatLng());
                from.setText(place.getAddress());
            }
        } else if (requestCode == 512) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                destination = place;
                Log.d(TAG, (String) place.getName());
                toLoc = new LocationDirections();
                toLoc.setName((String) place.getName());
                toLoc.setAddress((String) place.getAddress());
                toLoc.setLatLng(place.getLatLng());
                to.setText(place.getAddress());
            }

        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            // TODO: Handle the error.
            Log.i(TAG, status.getStatusMessage());

        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
    }
}
