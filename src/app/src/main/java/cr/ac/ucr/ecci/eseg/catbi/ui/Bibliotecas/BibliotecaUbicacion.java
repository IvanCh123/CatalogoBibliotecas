package cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class BibliotecaUbicacion extends AppCompatActivity  implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Boolean controlsEnabled = false;
    private static final int LOCATION_REQUEST_CODE = 101;
    private FusedLocationProviderClient fusedLocationClient;
    private String nombre;
    private double l1;
    private double l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca_ubicacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent=getIntent();
        nombre=intent.getStringExtra("name");
        l1=intent.getDoubleExtra("L1",90);
        l2=intent.getDoubleExtra("L2",90);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_REQUEST_CODE);
        }
        marcadorBiblioteca();
        posicionCamaraZoom();

    }
    protected void solicitarPermiso(String permissionType, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mapa_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_1) {
            marcadorBiblioteca();
            return true;
        } else if (id == R.id.buscar_usuario) {
            mostrarZoom();
            return true;
        } else if (id == R.id.action_3) {
            posicionCamaraZoom();
            return true;
        } else if (id == R.id.action_4) {
            mapaSatelite();
            return true;
        } else if (id == R.id.action_5) {
            mapaHibrido();
            return true;
        } else if (id == R.id.action_6) {
            mapaNinguno();
            return true;
        } else if (id == R.id.action_7) {
            mapaNormal();
            return true;
        } else if (id == R.id.action_8) {
            mapaTierra();
            return true;
        } else if (id == R.id.action_9) {

            obtenerLocalizacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void marcadorBiblioteca() {
        LatLng biblioteca = new LatLng(l1, l2);
        mMap.addMarker(new MarkerOptions().position(biblioteca).title(nombre));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(biblioteca));
    }
    public void mostrarZoom() {
        controlsEnabled = !controlsEnabled;
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(controlsEnabled);
        mapSettings.setCompassEnabled(controlsEnabled);
        mapSettings.setMyLocationButtonEnabled(controlsEnabled);
    }


    public void posicionCamaraZoom() {
        LatLng biblioteca = new LatLng(l1, l2);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(biblioteca)
                .zoom(20) // zoom level
                .bearing(70) // bearing // direccion de la camara
                .tilt(25) // tilt angle // inclinacion
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void mapaSatelite() { mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
    public void mapaHibrido() { mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
    public void mapaNinguno() { mMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
    public void mapaNormal() { mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    public void mapaTierra() { mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }

    private void obtenerLocalizacion() {
        if (mMap == null) return;
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            return;
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(new LatLng(location.getLatitude(),
                                            location.getLongitude()))
                                    .zoom(20)
                                    .bearing(70)
                                    .tilt(25)
                                    .build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    }
                });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        Toast.makeText(getApplicationContext(), latLng.toString(),
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(latLng.toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Toast.makeText(getApplicationContext(),
                "Nuevo marcador: " + latLng.toString(), Toast.LENGTH_LONG).show();
    }
}