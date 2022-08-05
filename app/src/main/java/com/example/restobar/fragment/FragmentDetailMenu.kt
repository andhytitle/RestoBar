package com.example.restobar.fragment

import android.content.Context
import android.content.Intent
import android.content.LocusId
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.restobar.List_Detail_Menu
import com.example.restobar.NoMeja
import com.example.restobar.R
import com.example.restobar.dataobject.DataDapur
import com.example.restobar.dataobject.DataPesanan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class FragmentDetailMenu : Fragment() {
    lateinit var categooryMakanan: TextView
    lateinit var namaMenu: TextView
    lateinit var gambarView: ImageView
    lateinit var descMenu: TextView
    lateinit var hargaMenu: TextView
    lateinit var btnPesan: Button
    private lateinit var mDbRef: DatabaseReference
    private lateinit var preferences: SharedPreferences

    companion object {
        var EXTRA_CATEGORY = "extra_category"
        var EXTRA_NAMA = "extra_nama"
        var EXTRA_GAMBAR = "extra_gambar"
        var EXTRA_DESCRIPTION = "extra_description"
        var EXTRA_HARGA = "extra_harga"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_menu, container, false)
        categooryMakanan = view.findViewById(R.id.tvKategori)
        namaMenu = view.findViewById(R.id.tvnamaProduk)
        gambarView = view.findViewById(R.id.ivGambarProduk)
        descMenu = view.findViewById(R.id.tvDeskProduk)
        hargaMenu = view.findViewById(R.id.tvHargaProduk)
        btnPesan = view.findViewById(R.id.btnDetailPesan)
        mDbRef = FirebaseDatabase.getInstance().reference

        preferences = requireActivity().getSharedPreferences("NOMEJA", Context.MODE_PRIVATE)
        val noMejaData = preferences.getString("noME", "")

        val args = this.arguments
        val nama = args?.getString(EXTRA_NAMA)
        namaMenu.text = nama

        categooryMakanan.text = args?.getString(EXTRA_CATEGORY)
        val gambar = args?.getInt(EXTRA_GAMBAR, 0)
        gambarView.setImageResource(gambar!!)
        // Cara Kedua menarik gambar
//        gambarView.setImageResource(args?.getInt(EXTRA_GAMBAR, 0)!!)
        descMenu.text = args?.getString(EXTRA_DESCRIPTION)
        val harga = args?.getInt(EXTRA_HARGA, 0)
        hargaMenu.text = formatRupiah(harga.toDouble())
        val id = mDbRef.push().key


        btnPesan.setOnClickListener {
            val calendar = Calendar.getInstance()
            val format = SimpleDateFormat(" EEEE d MM yyyy HH:mm:ss")
            val time: String = format.format(calendar.time)
            if (noMejaData!!.isNotEmpty()) {
                val intent = Intent(activity, List_Detail_Menu::class.java)
                sendFirebae(nama!!, harga, id!!)
                sendFirebaseDapur(noMejaData!!, nama, time, id)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), NoMeja::class.java)
                startActivity(intent)
            }


        }
        return view
    }

    private fun formatRupiah(number: Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
    }

    private fun sendFirebaseDapur(noMeja: String, namaMenu: String, tanggal: String, id: String) {
        mDbRef.child("Dapur").child(id)
            .setValue(DataDapur(noMeja, namaMenu, tanggal, id))
    }

    private fun sendFirebae(nama: String, harga: Int, id: String) {
        mDbRef.child("Pesanan").child(id).setValue(DataPesanan(nama, harga, id))
    }

}