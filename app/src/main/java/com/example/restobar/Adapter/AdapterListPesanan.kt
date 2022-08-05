package com.example.restobar.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar.R
import com.example.restobar.dataobject.DataPesanan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdapterListPesanan(private val listPesanan: ArrayList<DataPesanan>) :
    RecyclerView.Adapter<AdapterListPesanan.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaSpare = itemView.findViewById<TextView>(R.id.tvNamaMenuRV)
        val totalHarga = itemView.findViewById<TextView>(R.id.tvTotalHargaRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_pesanan, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listPesanan[position]
        holder.namaSpare.text = item.nama
        holder.totalHarga.text = item.harga.toString()

        holder.itemView.setOnClickListener {
            val mDeRef: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Pesanan").child(
                    item.id!!
                )
            val md: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Dapur").child(
                    item.id!!
                )
            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Hapus")
            alert.setMessage("Anda Ingin Hapus?")
            alert.setPositiveButton("Yes") { dialog, which ->
                mDeRef.removeValue()
                md.removeValue()
                listPesanan.removeAt(position)
            }
            alert.setNegativeButton("NO") { dialog, which ->
                dialog.dismiss()
            }
            alert.show()
        }

    }

    override fun getItemCount(): Int {
        return listPesanan.size
    }

}

