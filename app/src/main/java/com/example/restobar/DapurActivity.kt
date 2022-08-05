package com.example.restobar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar.Adapter.AdapterDapur
import com.example.restobar.dataobject.DataDapur
import com.google.firebase.database.*

class DapurActivity : AppCompatActivity() {
    private lateinit var rvDapur: RecyclerView
    private lateinit var listDapur: ArrayList<DataDapur>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dapur)

        listDapur = arrayListOf()
        rvDapur = findViewById(R.id.rvDapur)
        rvDapur.layoutManager = LinearLayoutManager(this)
        rvDapur.setHasFixedSize(true)
        rvDapur.adapter = AdapterDapur(listDapur)

        mDbRef = FirebaseDatabase.getInstance().reference

        preferences = this.getSharedPreferences("UNICE", Context.MODE_PRIVATE)
        val nic = preferences.getString("nic", "")


        mDbRef.child("$nic").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    listDapur.clear()
                    for (bengkel in snapshot.children) {
                        val bengkelList = bengkel.getValue(DataDapur::class.java)
                        listDapur.add(0, bengkelList!!)
                    }
                }
                rvDapur.adapter = AdapterDapur(listDapur)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}