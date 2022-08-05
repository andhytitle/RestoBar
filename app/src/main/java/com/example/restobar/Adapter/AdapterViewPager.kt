package com.example.restobar.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restobar.fragment.FragmentMenuDessert
import com.example.restobar.fragment.FragmentMenuMakanan
import com.example.restobar.fragment.FragmentMenuMinuman

class AdapterViewPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = FragmentMenuMakanan()
            1 -> fragment = FragmentMenuMinuman()
            2 -> fragment = FragmentMenuDessert()
        }
        return fragment
    }
}