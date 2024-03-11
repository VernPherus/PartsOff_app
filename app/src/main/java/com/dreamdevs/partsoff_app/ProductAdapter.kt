package com.dreamdevs.partsoff_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData

class ProductAdapter(private var productList: List<ProductsData>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {

    interface OnItemListener {
        fun onItemClick(position: Int)
    }

    var productListFiltered = productList
    private lateinit var clickListener: OnItemListener

    fun setOnItemClickListener(listener: OnItemListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_product, parent, false)
        return ProductViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int {
        return productListFiltered.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productListFiltered[position]
//        holder.productId.text = currentItem.id.toString()
        holder.productTitle.text = currentItem.title
        holder.productDesc.text = currentItem.description.toString()
        holder.productPrice.text = currentItem.price.toString()
        holder.productQty.text = currentItem.qty.toString()
        val imageUrl = currentItem.productImages.firstOrNull()?.image
        imageUrl?.let {
            Glide.with(holder.itemView.context)
                .load("http://64.23.185.162/uploads/product/large/$it")
                .into(holder.productImageView)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                productListFiltered = if (charSearch.isEmpty()) {
                    productList
                } else {
                    val resultList = ArrayList<ProductsData>()
                    for (row in productList) {
                        if (row.title!!.contains(charSearch, true)) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productListFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productListFiltered = results?.values as ArrayList<ProductsData>
                notifyDataSetChanged()
            }
        }
    }

    class ProductViewHolder(itemView: View, listener: OnItemListener) : RecyclerView.ViewHolder(itemView) {
//        val productId: TextView = itemView.findViewById(R.id.product_id)
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productDesc: TextView = itemView.findViewById(R.id.product_desc)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productQty: TextView = itemView.findViewById(R.id.product_qty)
        val productImageView: ImageView = itemView.findViewById(R.id.product_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }
    }
}
