package com.kuliah.rumahumkm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterKelas extends RecyclerView.Adapter<CustomAdapterKelas.MyViewHolder> {

    Context context;
    ArrayList id_kelas, nama_kelas, kategori_kelas;

    CustomAdapterKelas(Context context, ArrayList id_kelas, ArrayList nama_kelas, ArrayList kategori_kelas) {
        this.context = context;
        this.id_kelas = id_kelas;
        this.nama_kelas = nama_kelas;
        this.kategori_kelas = kategori_kelas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kelas_populer_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNamaKelas.setText(String.valueOf(nama_kelas.get(position)));
        holder.txtKategoriKelas.setText(String.valueOf(kategori_kelas.get(position)));
    }

    @Override
    public int getItemCount() {
        return nama_kelas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaKelas, txtKategoriKelas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaKelas = itemView.findViewById(R.id.txtKelasPopuler);
            txtKategoriKelas = itemView.findViewById(R.id.txtKategoriKelasPopuler);
        }
    }
}
