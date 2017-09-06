package id.gomus.gosparring;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import id.gomus.gosparring.model.TeamModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    ArrayList<TeamModel> listTeam;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private FusedLocationProviderClient mFusedLocationClient;
    private List<Marker> markers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listTeam = new ArrayList<>();
        listTeam = getIntent().getParcelableArrayListExtra("DATA_TEAM");

        for (int i = 0; i < listTeam.size(); i++) {
            Log.d(TAG, "onCreate: " + listTeam.get(i).getName());
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
        for (int i = 0; i < listTeam.size(); i++) {
            Double latitude = Double.parseDouble(listTeam.get(i).getLat());
            Double longitude = Double.parseDouble(listTeam.get(i).getLng());
            Log.d(TAG, "onMapReady: lokasi"+ listTeam.get(i).getLat()+ listTeam.get(i).getLat());
            LatLng lokasi = new LatLng(latitude, longitude);
//            String alamat = setGeoCoder(listTeam.get(i).getLokasiTeam());


            MarkerOptions markerOptions = new MarkerOptions()
                    .position(lokasi)
                    .title(listTeam.get(i).getName())
                    .snippet(listTeam.get(i).getAsal())
                    .visible(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerteam));
            Marker marker = mMap.addMarker(markerOptions);
            markers.add(marker);

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    View infoview = getLayoutInflater().inflate(R.layout.custom_info_window, null);
                    TextView infoNama = (TextView) infoview.findViewById(R.id.tv_info_nama);
                    TextView infoAsal = (TextView) infoview.findViewById(R.id.tv_info_asal);
                    TextView infoPoint = (TextView) infoview.findViewById(R.id.tv_info_point);
                    infoNama.setText(marker.getTitle());
                    infoAsal.setText(marker.getSnippet());
//                    int posisi = marker.get;
//                    infoPoint.setText(listTeam.get().getPoint());
                    Button infoAjakDuel = (Button) infoview.findViewById(R.id.btnAjakDuel);
//                    infoAjakDuel.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            Toast.makeText(MapsActivity.this, "Dueellll", Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
//                    });
                    return infoview;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
//                    Toast.makeText(MapsActivity.this, "Toiassss", Toast.LENGTH_SHORT).show();
                    Intent pindah = new Intent(MapsActivity.this, FormAjakDuel.class);
                    pindah.putExtra("DATA_TEAM", marker.getTitle());
                    startActivity(pindah);
                }
            });
        }

        if (Build.VERSION.SDK_INT >= 23) {
            cekPermisi();
            Log.d(TAG, "onMapReady: sdk>23");
        } else {
            getLocation();
            Log.d(TAG, "onMapReady: sdk<22");
        }
    }

    private void cekPermisi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSION);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Success", Toast.LENGTH_SHORT).show();
                getLocation();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            LatLng lokasiku = new LatLng(location.getLatitude(), location.getLongitude());
                            drawMarkerWithCircle(lokasiku);
                            Log.d(TAG, "LOKASIKU: " + location.getLatitude() + location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasiku, 12));

                            for (Marker marker : markers) {
                                Log.d(TAG, "getLocation: distance lokasi"+SphericalUtil.computeDistanceBetween(lokasiku, marker.getPosition()));
                                if (SphericalUtil.computeDistanceBetween(lokasiku, marker.getPosition()) < 3000) {
                                    marker.setVisible(true);
                                }
                            }
                        }
                    }
                });




        Log.d(TAG, "getLocation(): ");
    }

    private void drawMarkerWithCircle(LatLng lokasi) {
        double radiusInMeters = 3000.0;
        int strokeColor = 0xff21b6ff; //red outline
        int shadeColor = 0x4421b6ff; //opaque red fill

        CircleOptions circleOptions = new CircleOptions()
                .center(lokasi)
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(8);
        mMap.addCircle(circleOptions);
        Log.d(TAG, "drawMarkerWithCircle: ");
    }

//    public String setGeoCoder(LatLng latLng) {
//        double posisiLatitude = latLng.latitude;
//        double posisiLongitude = latLng.longitude;
//
//        Geocoder geocoder = new Geocoder(this);
//        List<Address> addresses = null;
//        String addressText = "";
//
//        try {
//            addresses = geocoder.getFromLocation(posisiLatitude, posisiLongitude, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (addresses != null && addresses.size() > 0) {
//            android.location.Address address = addresses.get(0);
//            addressText = String.format("%s, %s, %s",
//                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
//                    address.getLocality(),
//                    address.getCountryName());
//        }
//
//        Log.d(TAG, "setGeoCoder() returned: " + addressText);
//
//        return addressText;
//    }
}
