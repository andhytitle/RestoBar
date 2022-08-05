package com.example.restobar.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restobar.R
import com.example.restobar.dataobject.DataMenuView
import com.example.restobar.fragment.FragmentDetailMenu

class AdapterMakanan(private val listMenu: ArrayList<DataMenuView>) :
    RecyclerView.Adapter<AdapterMakanan.MenuViewHolder>() {
    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardProductList = itemView.findViewById<CardView>(R.id.cvProdukMenu)
        var imageMenu: ImageView = itemView.findViewById(R.id.ivProductImage)
        var textMenu: TextView = itemView.findViewById(R.id.tvProductName)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_data, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = listMenu[position]
        Glide.with(holder.itemView.context)
            .load(menu.photo)
            .apply(RequestOptions().override(150, 130))
            .into(holder.imageMenu)
        holder.textMenu.text = menu.name

        holder.cardProductList.setOnClickListener {
            val ft =
                (holder.cardProductList.context as FragmentActivity).supportFragmentManager.beginTransaction()
            val fragmentDetailMenu = FragmentDetailMenu()
            val bundle = Bundle()
            bundle.putString(FragmentDetailMenu.EXTRA_CATEGORY, menu.category)
            bundle.putString(FragmentDetailMenu.EXTRA_NAMA, menu.name)
            bundle.putInt(FragmentDetailMenu.EXTRA_GAMBAR, menu.photo)
            bundle.putString(FragmentDetailMenu.EXTRA_DESCRIPTION, menu.desc)
            bundle.putInt(FragmentDetailMenu.EXTRA_HARGA, menu.price)
            fragmentDetailMenu.arguments = bundle




            ft!!.replace(R.id.layoutFragment, fragmentDetailMenu)
            ft!!.addToBackStack(null)
            ft!!.commit()
        }

    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}