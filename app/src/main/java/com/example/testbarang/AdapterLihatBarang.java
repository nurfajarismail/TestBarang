package com.example.testbarang;

import android.app.Activity;
import android.app.Dialog;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterLihatBarang extends
        RecyclerView.Adapter<AdapterLihatBarang.ViewHolder> {
    private ArrayList<Barang> daftarBarang;
    private Context context;

    public AdapterLihatBarang(ArrayList<Barang> barangs, Context ctx) {
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBarang = barangs;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Inisiasi View
         * Disini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * Inisiasi ViewHolder
         */
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent,
                        false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         * Menampilkan data pada view
         */
        final String name = daftarBarang.get(position).getNama();
        final String kode = daftarBarang.get(position).getKode();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * untuk latihan Selanjutnya , jika ingin membaca detail data
                 */


            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 * untuk latihan Selanjutnya ,fungsi Delete dan Update data
                 */

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.buat_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button updateButton = (Button) dialog.findViewById(R.id.btnUpdate);


                //apabila tombol edit diklik
                updateButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(TambahData.getActIntent((Activity) context).putExtra("data",daftarBarang.get(position)));
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBarang.size();
    }
}
