package id.gomus.gosparring;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.gomus.gosparring.model.SparringModel;

/**
 * Created by idn on 8/6/2017.
 */

public class SparringAdapter extends RecyclerView.Adapter<SparringAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SparringModel> listSparring;
    private Cursor mCursor;
    //generate constructor

    public SparringAdapter(Context context, ArrayList<SparringModel> listSparring) {
        this.context = context;
        this.listSparring = listSparring;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //menghubungkan dengan movie_item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sparring_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //ngapain
        //set data

        holder.teamA.setText(listSparring.get(position).getTeamA());
        holder.teamB.setText(listSparring.get(position).getTeamB());
        holder.kotaA.setText(listSparring.get(position).getKotaA());
        holder.kotaB.setText(listSparring.get(position).getKotaB());
        holder.lapangan.setText(listSparring.get(position).getLapangan());
        holder.hari.setText(listSparring.get(position).getHari());
        holder.waktu.setText(listSparring.get(position).getWaktu());
        if (listSparring.get(position).getStatus().equals("riwayat")){
            holder.skorA.setText(listSparring.get(position).getSkorA());
            holder.skorB.setText(listSparring.get(position).getSkorB());
            holder.strip.setVisibility(View.VISIBLE);
        }


        //setOnClick
//        holder.ivposterMovie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Kliked posisi "+ position, Toast.LENGTH_SHORT).show();
//                // Intent ke DetailActivity
//                Intent pindah = new Intent(context, DetailActivity.class);
//                //kirim data
//                pindah.putExtra("DATA_ID", listSparring.get(position).getId());
//                pindah.putExtra("DATA_JUDUL", listSparring.get(position).getJudul());
//                pindah.putExtra("DATA_POSTER", listSparring.get(position).getPoster());
//                context.startActivity(pindah);
//            }
//        });

    }

    @Override
    public int getItemCount() {
//        jumlah list
//        return listSparring.size();
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return listSparring.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //menyambungkan dengan komponen yg di dalam item
        TextView teamA, teamB, kotaA, kotaB, lapangan, hari, waktu, skorA, skorB, strip;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            teamA = (TextView) itemView.findViewById(R.id.tv_item_teamA);
            kotaA = (TextView) itemView.findViewById(R.id.tv_item_kotaA);
            teamB = (TextView) itemView.findViewById(R.id.tv_item_teamB);
            kotaB = (TextView) itemView.findViewById(R.id.tv_item_kotaB);
            lapangan = (TextView) itemView.findViewById(R.id.tv_item_lapangan);
            hari = (TextView) itemView.findViewById(R.id.tv_item_hari);
            waktu = (TextView) itemView.findViewById(R.id.tv_iten_waktu);
            skorA = (TextView) itemView.findViewById(R.id.tv_item_skorA);
            skorB = (TextView) itemView.findViewById(R.id.tv_item_skorB);
            strip = (TextView) itemView.findViewById(R.id.tv_item_strip);

        }
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

}
