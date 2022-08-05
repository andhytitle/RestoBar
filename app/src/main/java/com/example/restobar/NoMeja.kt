package com.example.restobar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NoMeja : AppCompatActivity() {
    private lateinit var noMeja: TextView
    private lateinit var btnSaveNoMeja: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_meja)
        noMeja = findViewById(R.id.etNomorMeja)
        btnSaveNoMeja = findViewById(R.id.btnSaveNoMeja)

        btnSaveNoMeja.setOnClickListener {
            sharedPreferences = getSharedPreferences("NOMEJA", Context.MODE_PRIVATE)
            val noMej = noMeja.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("noME", noMej)
            editor.apply()

            if (noMej.isEmpty()) {
                noMeja.error = "Masukkan Nomor Kendaraan"
                noMeja.requestFocus()
            } else {
                val intent = Intent(this, List_Detail_Menu::class.java)
                startActivity(intent)
            }
        }


    }
}