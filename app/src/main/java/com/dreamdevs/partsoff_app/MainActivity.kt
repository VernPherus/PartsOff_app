package com.dreamdevs.partsoff_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newRecyclerview : RecyclerView
    private lateinit var newArrayList : ArrayList<Products>
    private lateinit var title : Array<String>
    private lateinit var description : Array<Any>
    private lateinit var price : Array<Int>
    private lateinit var qty : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = arrayOf(
            "RTX 2060 super",
            "RX 5700 xt",
            "AMD Ryzen 7 7800x3d",
            "Adata 950 Legend",
            "HDMI Cable"
        )

        description = arrayOf(
            "product desc",
            "lorem ipsum dolor amet man I dunno I don't speak latin shut yo bitch ahh up this why yo mama dead, this why yo daddy ain't commin back with the milk",
            "product desc",
            "product desc",
            "product desc"
        )

        price = arrayOf(
            21000,
            15000,
            20000,
            3000,
            250
        )

        qty = arrayOf(
            21,
            15,
            20,
            30,
            25
        )

        newRecyclerview = findViewById(R.id.productsRecycler)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.setHasFixedSize(true)

        newArrayList = arrayListOf<Products>()
        getProductData()


    }

    private fun getProductData() {

        for(i in title.indices){
            val product = Products(title[i], description[i], price[i], qty[i])
            newArrayList.add(product)
        }

        newRecyclerview.adapter = ProductAdapter(newArrayList)

    }


}
