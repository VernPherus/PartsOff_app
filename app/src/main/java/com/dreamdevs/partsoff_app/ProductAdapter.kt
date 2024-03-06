package com.dreamdevs.partsoff_app


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products

class ProductAdapter(private val productList : ArrayList<Products>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var clickListener : onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_product, parent, false)
        return ProductViewHolder(itemView, clickListener)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.productTitle.text = currentItem.title
        holder.productDesc.text = currentItem.description.toString()
        holder.productPrice.text = currentItem.price.toString()
        holder.productQty.text = currentItem.qty.toString()
    }

    class ProductViewHolder(itemView: View, listener: onItemListener) : RecyclerView.ViewHolder(itemView){

        val productTitle : TextView = itemView.findViewById(R.id.product_title)
        val productDesc : TextView = itemView.findViewById(R.id.product_desc)
        val productPrice : TextView = itemView.findViewById(R.id.product_price)
        val productQty : TextView = itemView.findViewById(R.id.product_qty)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: onItemListener){

        clickListener = listener


    }

}
