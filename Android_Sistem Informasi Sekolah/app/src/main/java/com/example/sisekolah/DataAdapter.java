package com.example.sisekolah;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sisekolah.helper.URLs;
import com.example.sisekolah.model.Announcement;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private Context context;
    private List<Announcement> dataList;
    private RecyclerView mRecyclerView;

    public DataAdapter(Context context, List<Announcement> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int view){
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int itemPosition = mRecyclerView.getChildLayoutPosition(view1);
//                Intent intentData = new Intent(view1.getContext(), DetailPengumumanActivity.class);
//                intentData.putExtra(URLs.CURRENT_ID, dataList.get(itemPosition).getId());
//                intentData.putExtra(URLs.CURRENT_TITLE, dataList.get(itemPosition).getJudul());
//                intentData.putExtra(URLs.CURRENT_CONTENT, dataList.get(itemPosition).getIsi());
//            }
//        });
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.idPengumuman.setText(dataList.get(position).getId());
        holder.judulPengumuman.setText(dataList.get(position).getJudul());
        holder.isiPengumuman.setText(dataList.get(position).getIsi());
    }


    @Override
    public int getItemCount(){
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView idPengumuman, judulPengumuman, isiPengumuman;

        public MyViewHolder(View itemView){
            super(itemView);
            idPengumuman = (TextView) itemView.findViewById(R.id.idPengumuman);
            idPengumuman.setVisibility(View.GONE);
            judulPengumuman = (TextView) itemView.findViewById(R.id.judulPengumuman);
            isiPengumuman = (TextView) itemView.findViewById(R.id.isiPengumuman);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailPengumumanActivity.class));
                    try{
                        Intent intentData = new Intent(itemView.getContext(), DetailPengumumanActivity.class);
                        intentData.putExtra(URLs.CURRENT_ID, dataList.get(getAdapterPosition()).getIsi());
                        intentData.putExtra(URLs.CURRENT_TITLE, dataList.get(getAdapterPosition()).getJudul());
                        intentData.putExtra(URLs.CURRENT_CONTENT, dataList.get(getAdapterPosition()).getIsi());
                        itemView.getContext().startActivity(intentData);
                    }catch (@NonNull Throwable e){
                        Log.e("Error", e.getMessage());
                    }
                }
            });
        }
    }
}
