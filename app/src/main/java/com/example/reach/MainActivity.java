package com.example.reach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//Requires that you install Google Play Services Using the SDK Manager
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "in onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu_items, m);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id){
            case R.id.create:
                Intent intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
        }
        return true;
    }

    public void onMapReady(GoogleMap map){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(43.473868, -80.525318))
                .zoom(13)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
