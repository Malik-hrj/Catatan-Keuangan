package com.example.catatankeuangan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catatankeuangan.model.Keuangan;

import java.util.ArrayList;

public class KeuanganAdapter extends RecyclerView.Adapter<KeuanganAdapter.KeuanganViewHolder> {

    private ArrayList<Keuangan> arrayList = new ArrayList<>();

    public void setArrayList(ArrayList<Keuangan> arrayList) {
        this.arrayList = arrayList;
    }

    private Activity activity;

    public KeuanganAdapter (Activity activity) {

        this.activity = activity;
    }

    @NonNull
    @Override
    public KeuanganAdapter.KeuanganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_keuangan, parent, false) ;
        return new KeuanganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeuanganViewHolder holder, int position) {
        holder.tvNominal.setText(String.valueOf(arrayList.get(position).getNominal()));
        holder.tvKeterangan.setText(String.valueOf(arrayList.get(position).getKeterangan()));
        holder.tvTanggal.setText(String.valueOf(arrayList.get(position).getTanggal()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class KeuanganViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNominal, tvKeterangan, tvTanggal;


        public KeuanganViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNominal = itemView.findViewById(R.id.tv_nominal);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
