package com.example.restobar.dataobject

import com.example.restobar.R

object DataMenu {
    private val menuMakanan = arrayOf(
        "Batagor",
        "Nasi Goreng",
        "Sate",
        "Rawon",
        "Coto",
        "Gado"
    )
    private val category = arrayOf(
        "Makanan",
        "Minuman",
        "Dessert"
    )

    private val descMakanan = arrayOf(
        "Batagor Makanan Khas Indonesia",
        "Nasi Goreng Makanan Khas Indonesia yang mendunia",
        "Sate adalah makanan yang diolah dari bahan daging",
        "Rawon adalah makanan khas indonesia yang mendunia",
        "Coto adalah Makanan khas Makassar yang terbuat dari bahan olahan daging",
        "GAdo Da"

    )
    private val descMinuman = arrayOf(
        "Teh Tarik minuman mix dari susu dan teh",
        "Teh Manis adalah minuman khas sejuta umat",
        "Jus Alpuket Minuman dari olahan Buah Alpukat",
        "Jus Mangga Minuman dari olahan Buah Mangga",
        "Jus Apel Minuman dari olahan Buah Apel"
    )

    private val descDessert = arrayOf(
        "Pancake adalah hidangan penutup",
        "Tiramisu adalah hidangan penutup rasa coffe dan cream",
        "Hongtang adalah hidangan penutup khas korea",
        "Wagashi adalah kue beras khas jepang",
        "kue mungil yang dibuat dari adonan kue yang dicetak dalam paper cup atau cup kertas."
    )

    private val menuMinuman = arrayOf(
        "Teh Tarik",
        "Teh Manis",
        "Jus Alpukat",
        "Jus Mangga",
        "Jus Apel"
    )

    private val hargaMakanan = arrayOf(
        10000,
        12000,
        15000,
        12000,
        15000,
        10000
    )
    private val hargaMinuman = arrayOf(
        10000,
        12000,
        15000,
        12000,
        15000
    )
    private val hargaDessert = arrayOf(
        10000,
        12000,
        15000,
        12000,
        15000
    )


    private val menuDessert = arrayOf(
        "Pancake",
        "Tiramisu",
        "Hong Tang",
        "Wagashi",
        "Cup Cake"
    )

    private val menuMakananGambar = arrayOf(
        R.drawable.batagor,
        R.drawable.nas_goreng,
        R.drawable.sate,
        R.drawable.rawon,
        R.drawable.coto,
        R.drawable.rawon
    )
    private val menuMinumanGambar = arrayOf(
        R.drawable.tehtarik,
        R.drawable.teh_manis,
        R.drawable.jusalpuket,
        R.drawable.juice_mangga,
        R.drawable.jusapel
    )

    private val menuDessertGambar = arrayOf(
        R.drawable.pancake,
        R.drawable.tiramisu,
        R.drawable.hongtang,
        R.drawable.wagasi,
        R.drawable.cupcake
    )

    val listDataMenu: ArrayList<DataMenuView>
        get() {
            val list = arrayListOf<DataMenuView>()
            for (position in menuMakanan.indices) {
                val dataMenu = DataMenuView()
                dataMenu.name = menuMakanan[position]
                dataMenu.photo = menuMakananGambar[position]
                dataMenu.desc = descMakanan[position]
                dataMenu.category = category[0]
                dataMenu.price = hargaMakanan[position]
                list.add(dataMenu)
            }
            return list
        }
    val listDataMenuMinuman: ArrayList<DataMenuView>
        get() {
            val list = arrayListOf<DataMenuView>()
            for (position in menuMinuman.indices) {
                val dataMenu = DataMenuView()
                dataMenu.name = menuMinuman[position]
                dataMenu.photo = menuMinumanGambar[position]
                dataMenu.desc = descMinuman[position]
                dataMenu.category = category[1]
                dataMenu.price = hargaMinuman[position]
                list.add(dataMenu)
            }
            return list
        }
    val listDataMenuDessert: ArrayList<DataMenuView>
        get() {
            val list = arrayListOf<DataMenuView>()
            for (position in menuDessert.indices) {
                val dataMenu = DataMenuView()
                dataMenu.name = menuDessert[position]
                dataMenu.photo = menuDessertGambar[position]
                dataMenu.desc = descDessert[position]
                dataMenu.category = category[2]
                dataMenu.price = hargaDessert[position]
                list.add(dataMenu)
            }
            return list
        }
}