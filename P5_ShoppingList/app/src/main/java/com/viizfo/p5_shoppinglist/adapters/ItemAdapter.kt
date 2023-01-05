package com.viizfo.p5_shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.p5_shoppinglist.R
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity

class ItemAdapter(
    val items: List<ItemEntity>,
    val checkItem: (ItemEntity) -> Unit,
    val deleteItem: (ItemEntity) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_shoppinglist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, checkItem, deleteItem)
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvItem = view.findViewById<TextView>(R.id.tvName)
        val tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)
        val ibtnDelete = view.findViewById<ImageButton>(R.id.ibtnDelete)

        fun bind(item: ItemEntity, checkItem: (ItemEntity) -> Unit, deleteItem: (ItemEntity) -> Unit) {
            tvItem.text = item.name
            tvQuantity.text = item.quantity.toString()
            tvPrice.text = item.price.toString()
            tvTotalPrice.text = String.format("%.2f",(item.price * item.quantity))
            ibtnDelete.setOnClickListener { deleteItem(item) }
        }
    }
}