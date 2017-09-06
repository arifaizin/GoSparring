package id.gomus.gosparring.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.gomus.gosparring.R;
import id.gomus.gosparring.SparringAdapter;
import id.gomus.gosparring.model.SparringModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends Fragment {

    RecyclerView recycler;
    ArrayList<SparringModel> listSparring;
    SparringAdapter adapter;
    
    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //dataset
        //1 Buat Model datanya
        //2 ArrayList SparringModel
        listSparring = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SparringModel data1 = new SparringModel();
            data1.setTeamA("Garuda Team");
            data1.setTeamB("Meong Team");
            data1.setKotaA("Ambarawa");
            data1.setKotaB("Ungaran");
            data1.setLapangan("Lapangan Dewa Futsal");
            data1.setHari("Senin, 17 Agustus 1945");
            data1.setWaktu("10.00-11.00");
            data1.setStatus("jadwal");
            listSparring.add(data1);
        }

        //layoutmanager
        recycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //adapter
        adapter = new SparringAdapter(getActivity(), listSparring);
        recycler.setAdapter(adapter);

//        getDataOnline(linkurl);
    }

//    private void getDataOnline(String url) {
//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Mohon Bersabar");
//
//        JsonObjectRequest ambilData = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                loading.hide();
//                try {
//                    JSONArray arrayresults = response.getJSONArray("results");
//                    for (int i = 0; i < arrayresults.length(); i++) {
//                        JSONObject json = arrayresults.getJSONObject(i);
//                        Log.d("MainActivity", "Hasil json : " + json);
//
//                        SparringModel movie1 = new SparringModel();
//                        movie1.setId(json.getInt("id"));
//                        movie1.setJudul(json.getString("title"));
//                        movie1.setPoster(json.getString("poster_path"));
//                        listSparring.add(movie1);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "Error : " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue antrian = Volley.newRequestQueue(getActivity());
//        antrian.add(ambilData);
//
//
//    }

}
