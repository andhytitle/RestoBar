package com.example.restobar.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar.R
import com.example.restobar.dataobject.DataDapur
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdapterDapur(private val listDapur: ArrayList<DataDapur>) :
    RecyclerView.Adapter<AdapterDapur.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noKen = itemView.findViewById<TextView>(R.id.tvNokenBengkel)
        val namaSpare = itemView.findViewById<TextView>(R.id.tvNamaBengkel)
        val tanggalBe = itemView.findViewById<TextView>(R.id.tvTanggalBengkel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_dapur, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDapur[position]
        holder.noKen.text = item.noMeja
        holder.namaSpare.text = item.namaMenu
        holder.tanggalBe.text = item.tanggal


        holder.itemView.setOnClickListener {
            val mDeRef: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Bengkel").child(
                    item.id!!
                )
            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Konfirmasi")
            alert.setMessage("Pesan Telah Selesai?")
            alert.setPositiveButton("Ya") { dialog, which ->
                mDeRef.removeValue()
                listDapur.removeAt(position)
            }
            alert.setNegativeButton("Belum") { dialog, which ->
                dialog.dismiss()
            }
            alert.show()
        }


    }

    override fun getItemCount(): Int {
        return listDapur.size
    }
}