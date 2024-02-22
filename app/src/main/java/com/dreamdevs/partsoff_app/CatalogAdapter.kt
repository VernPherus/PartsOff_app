package com.dreamdevs.partsoff_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamdevs.partsoff_app.databinding.ItemCatalogBinding

class CatalogAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList.size

    data class Item(val name: String, val price: String, val imageUrl: String)

    class CatalogViewHolder(private val binding: ItemCatalogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            Glide.with(binding.itemImage.context).load(item.imageUrl).into(binding.itemImage)
            binding.itemName.text = item.name
            binding.itemPrice.text = item.price
        }
    }
}
