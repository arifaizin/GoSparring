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
public class RiwayatFragment extends Fragment {


    public RiwayatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //dataset
        //1 Buat Model datanya
        //2 ArrayList SparringModel
        ArrayList<SparringModel> listSparring = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SparringModel data1 = new SparringModel();
            data1.setTeamA("Garuda Team");
            data1.setTeamB("Macan Team");
            data1.setKotaA("Ambarawa");
            data1.setKotaB("Ungaran");
            data1.setLapangan("Lapangan Dewa Futsal");
            data1.setHari("Senin, 17 Agustus 1945");
            data1.setWaktu("10.00-11.00");
            data1.setSkorA("7");
            data1.setSkorB("7");
            data1.setStatus("riwayat");
            listSparring.add(data1);
        }

        //layoutmanager
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //adapter
        SparringAdapter adapter = new SparringAdapter(getActivity(), listSparring);
        recycler.setAdapter(adapter);

//        getDataOnline(linkurl);
    }


}
