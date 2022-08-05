package com.example.restobar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar.Adapter.AdapterMakanan
import com.example.restobar.R
import com.example.restobar.dataobject.DataMenu
import com.example.restobar.dataobject.DataMenuView

class FragmentMenuMakanan : Fragment() {
    private lateinit var rvMenuView: RecyclerView
    private var listMenuView: ArrayList<DataMenuView> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_makanan, container, false)
        rvMenuView = view.findViewById(R.id.rvMenuView)
        rvMenuView.setHasFixedSize(true)
        listMenuView.addAll(DataMenu.listDataMenu)
        showRecycleMenu()

        return view
    }

    private fun showRecycleMenu() {
        rvMenuView.layoutManager = GridLayoutManager(requireActivity(), 2)
        val gridMenu = AdapterMakanan(listMenuView)
        rvMenuView.adapter = gridMenu
    }


}