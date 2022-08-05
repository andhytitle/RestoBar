package com.example.restobar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restobar.fragment.FragmentHome

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentHome = FragmentHome()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layoutFragment, fragmentHome)
            commit()

        }


    }
}