package com.viizfo.p5_shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

        fun bind(item: ItemEntity, checkTask: (ItemEntity) -> Unit, deleteTask: (ItemEntity) -> Unit) {
            tvItem.text = item.name

            itemView.setOnClickListener { deleteTask(item) }
        }
    }
}