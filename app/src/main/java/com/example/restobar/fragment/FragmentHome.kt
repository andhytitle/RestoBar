package com.example.restobar.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.restobar.DapurActivity
import com.example.restobar.MenuTab
import com.example.restobar.NoMeja
import com.example.restobar.R

class FragmentHome : Fragment() {
    lateinit var btnMenu: Button
    lateinit var btnPesanan: Button
    lateinit var btnDapur: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_fragment_home, container, false)
        btnMenu = view.findViewById(R.id.btnMenu)
        btnPesanan = view.findViewById(R.id.btnPesanan)
        btnDapur = view.findViewById(R.id.btnDapur)

        btnMenu.setOnClickListener {
            val ft = fragmentManager?.beginTransaction()
            val fragmentMenuView = MenuTab()
            ft!!.replace(R.id.layoutFragment, fragmentMenuView)
            ft!!.addToBackStack(null)
            ft!!.commit()


        }

        btnPesanan.setOnClickListener {
            val intent = Intent(activity, NoMeja::class.java)
            startActivity(intent)
        }

        btnDapur.setOnClickListener {
            val intent = Intent(activity, DapurActivity::class.java)
            startActivity(intent)
        }


        return view
    }

}