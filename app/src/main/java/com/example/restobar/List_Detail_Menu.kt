package com.example.restobar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar.Adapter.AdapterListPesanan
import com.example.restobar.dataobject.DataDapur
import com.example.restobar.dataobject.DataPesanan
import com.example.restobar.fragment.FragmentMenuMakanan
import com.google.firebase.database.*

class List_Detail_Menu : AppCompatActivity() {
    private lateinit var rvlistPesanan: RecyclerView
    private lateinit var dataMenu: ArrayList<DataPesanan>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mDbSend: DatabaseReference
    private lateinit var preferences: SharedPreferences
    private lateinit var grandTotal: TextView
    private lateinit var btnSend: Button
    private lateinit var noMeja2: TextView


    private lateinit var btnTambahMenu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail_menu)
        btnSend = findViewById(R.id.btnKirimData)
        rvlistPesanan = findViewById(R.id.rvDetailPesanan)
        grandTotal = findViewById(R.id.tvGrandTotal)
        dataMenu = arrayListOf()
        rvlistPesanan.layoutManager = LinearLayoutManager(this)
        preferences = this.getSharedPreferences("NOMEJA", Context.MODE_PRIVATE)
        val noMeja = preferences.getString("noME", "")
        rvlistPesanan.adapter = AdapterListPesanan(dataMenu)

        noMeja2 = findViewById(R.id.tvNomorMeja)

        Toast.makeText(this, "$noMeja", Toast.LENGTH_SHORT).show()
        mDbRef = FirebaseDatabase.getInstance().getReference("Pesanan")
        noMeja2.text = noMeja
        mDbSend = FirebaseDatabase.getInstance().reference
        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    dataMenu.clear()
                    for (spare in snapshot.children) {
                        val spa = spare.getValue(DataPesanan::class.java)
                        dataMenu.add(0, spa!!)

                    }
                    var sum = 0
                    for (total in snapshot.children) {
                        val tot = total.getValue(DataPesanan::class.java)?.harga
//                        val pValue = tot
                        sum += tot!!
                        grandTotal.text = sum.toString()
                    }

                }
                rvlistPesanan.adapter = AdapterListPesanan(dataMenu)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        mDbSend = FirebaseDatabase.getInstance().reference
        btnTambahMenu = findViewById(R.id.btnTambahMenu)

        btnTambahMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

        btnSend.setOnClickListener {
            mDbRef = FirebaseDatabase.getInstance().getReference("Pesanan")
            mDbRef.removeValue()
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DapurActivity::class.java)
            preferences = getSharedPreferences("UNICE", Context.MODE_PRIVATE)
            val uni = "Dapur"
            val edi: SharedPreferences.Editor = preferences.edit()
            edi.putString("nic", uni)
            edi.apply()
            intent.putExtra("unice", "Bengkel")
            startActivity(intent)
        }
    }
}