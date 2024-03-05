package com.dreamdevs.partsoff_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products

class ProductAdapter(private var productList: ArrayList<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {

    var productListFiltered = productList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productListFiltered.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productListFiltered[position]
        holder.productTitle.text = currentItem.title
        holder.productDesc.text = currentItem.description.toString()
        holder.productPrice.text = currentItem.price.toString()
        holder.productQty.text = currentItem.qty.toString()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                productListFiltered = if (charSearch.isEmpty()) {
                    productList
                } else {
                    val resultList = ArrayList<Products>()
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
                productListFiltered = results?.values as ArrayList<Products>
                notifyDataSetChanged()
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productDesc: TextView = itemView.findViewById(R.id.product_desc)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productQty: TextView = itemView.findViewById(R.id.product_qty)
    }
}
