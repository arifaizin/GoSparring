package id.gomus.gosparring;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

import id.gomus.gosparring.fragment.AjakanFragment;
import id.gomus.gosparring.fragment.JadwalFragment;
import id.gomus.gosparring.fragment.RiwayatFragment;
import id.gomus.gosparring.model.TeamModel;
import id.gomus.gosparring.rest.Client;
import id.gomus.gosparring.rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<TeamModel> listTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listTeam = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TeamModel team1 = new TeamModel();
//            team1.setName("Team "+i);
//            team1.setAsal("Asal team "+i);
//            team1.setLat("-6.1923633");
//            team1.setLng("106.8391554");
////            team1.setLokasiTeam(new LatLng(-6.1923633+i,106.8391554+i));
//            listTeam.add(team1);
//        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainNavigationActivity.this, MapsActivity.class);
                pindah.putParcelableArrayListExtra("DATA_TEAM", listTeam);
                startActivity(pindah);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Ajakan Sparring", AjakanFragment.class)
                .add("Jadwal Sparring", JadwalFragment.class)
                .add("Riwayat Sparring", RiwayatFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        getDataTeam();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getDataTeam() {
        ApiService api = Client.getInstanceRetrofit();
        Call<ArrayList<TeamModel>> call = api.ambilDataTeam();

        call.enqueue(new Callback<ArrayList<TeamModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TeamModel>> call, Response<ArrayList<TeamModel>> response) {
                
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                listTeam = response.body();
                for (int i=0 ; i<listTeam.size() ; i++){
                    Log.d("Networktask Response",""+listTeam.get(i).getName());
                }
                //https://github.com/mathemandy/BakingApp/blob/master/app/src/main/java/com/ng/tselebro/bakingapp/recipe/MainActivity.java

            }

            @Override
            public void onFailure(Call<ArrayList<TeamModel>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(MainNavigationActivity.this, "http fail: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
