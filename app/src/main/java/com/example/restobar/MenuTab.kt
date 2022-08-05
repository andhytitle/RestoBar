package com.example.restobar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.restobar.Adapter.AdapterViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuTab : Fragment() {

    lateinit var adapterViewPager: AdapterViewPager
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_tab, container, false)
        adapterViewPager = AdapterViewPager(childFragmentManager, lifecycle)
        viewPager = view.findViewById(R.id.viewpager)
        tabLayout = view.findViewById(R.id.tabs)

        with(requireActivity()) {
            viewPager.adapter = adapterViewPager
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Makanan"
                    1 -> tab.text = "Minuman"
                    2 -> tab.text = "Dessert"
                }
            }.attach()
        }
        return view
    }
}


