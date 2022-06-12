package com.example.sisekolah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sisekolah.model.Pengumuman;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder>{
    private ArrayList<Pengumuman> PengumumanArrayList;

    public PengumumanAdapter(ArrayList<Pengumuman> PengumumanArrayList){
        this.PengumumanArrayList = PengumumanArrayList;
    }

    @NonNull
    @Override
    public PengumumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengumumanAdapter.ViewHolder holder, int i){
        holder.idPengumuman.setText(PengumumanArrayList.get(i).getId());
        holder.judulPengumuman.setText(PengumumanArrayList.get(i).getJudul());
        holder.isiPengumuman.setText(PengumumanArrayList.get(i).getIsi());
    }

    @Override
    public int getItemCount(){
        return PengumumanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idPengumuman, judulPengumuman, isiPengumuman;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            idPengumuman = (TextView) itemView.findViewById(R.id.idPengumuman);
            idPengumuman.setVisibility(View.GONE);
            judulPengumuman = (TextView) itemView.findViewById(R.id.judulPengumuman);
            isiPengumuman = (TextView) itemView.findViewById(R.id.isiPengumuman);
        }
    }
}
