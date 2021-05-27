package com.kuliah.rumahumkm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterSeminar extends RecyclerView.Adapter<CustomAdapterSeminar.MyViewHolder> {

    Context context;
    ArrayList id_seminar, nama_seminar, kategori_seminar;

    CustomAdapterSeminar(Context context, ArrayList id_seminar, ArrayList nama_seminar, ArrayList kategori_seminar) {
        this.context = context;
        this.id_seminar = id_seminar;
        this.nama_seminar = nama_seminar;
        this.kategori_seminar = kategori_seminar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.seminar_populer_row, parent, false);
        return new CustomAdapterSeminar.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNamaSeminar.setText(String.valueOf(nama_seminar.get(position)));
        holder.txtKategoriSeminar.setText(String.valueOf(kategori_seminar.get(position)));
    }

    @Override
    public int getItemCount() {
        return nama_seminar.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaSeminar, txtKategoriSeminar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaSeminar = itemView.findViewById(R.id.txtSeminarPopuler);
            txtKategoriSeminar = itemView.findViewById(R.id.txtKategoriSeminarPopuler);
        }
    }
}
