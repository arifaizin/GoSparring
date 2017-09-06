package id.gomus.gosparring;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import id.gomus.gosparring.model.LapanganModel;
import id.gomus.gosparring.rest.ApiService;
import id.gomus.gosparring.rest.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsLapanganActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    ArrayList<LapanganModel> arrayListData;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private FusedLocationProviderClient mFusedLocationClient;
    private List<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_lapangan);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getDataLapangan();
    }

    private void getDataLapangan() {
        ApiService api = Client.getInstanceRetrofit();
        Call<ArrayList<LapanganModel>> call = api.ambilDataLapangan();

        call.enqueue(new Callback<ArrayList<LapanganModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LapanganModel>> call, Response<ArrayList<LapanganModel>> response) {

                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                arrayListData = response.body();
                for (int i = 0; i < arrayListData.size(); i++) {
                    Log.d("Networktask Response", "" + arrayListData.get(i).getNama());
                }
                //https://github.com/mathemandy/BakingApp/blob/master/app/src/main/java/com/ng/tselebro/bakingapp/recipe/MainActivity.java

            }

            @Override
            public void onFailure(Call<ArrayList<LapanganModel>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(MapsLapanganActivity.this, "http fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        for (int i = 0; i < arrayListData.size(); i++) {
            Double latitude = Double.parseDouble(arrayListData.get(i).getLat());
            Double longitude = Double.parseDouble(arrayListData.get(i).getLng());
            Log.d(TAG, "onMapReady: lokasi" + arrayListData.get(i).getLat() + arrayListData.get(i).getLat());
            LatLng lokasi = new LatLng(latitude, longitude);
//            String alamat = setGeoCoder(arrayListData.get(i).getLokasiTeam());


            MarkerOptions markerOptions = new MarkerOptions()
                    .position(lokasi)
                    .title(arrayListData.get(i).getNama())
                    .snippet(arrayListData.get(i).getAlamat())
                    .visible(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerteam));
            Marker marker = mMap.addMarker(markerOptions);
            markers.add(marker);
        }
    }
}
