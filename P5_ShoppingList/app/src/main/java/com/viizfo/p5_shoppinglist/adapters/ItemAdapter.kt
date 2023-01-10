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
    private val items: List<ItemEntity>,
    private val updateItem: (ItemEntity) -> Unit,
    private val deleteItem: (ItemEntity) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_shoppinglist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item, updateItem, deleteItem)
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvItem: TextView = view.findViewById(R.id.tvName)
        private val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        private val tvTotalPrice: TextView = view.findViewById(R.id.tvTotalPrice)
        private val ibtnDelete: ImageButton = view.findViewById(R.id.ibtnDelete)

        fun bind(item: ItemEntity, updateItem: (ItemEntity) -> Unit, deleteItem: (ItemEntity) -> Unit) {
            tvItem.text = item.name
            tvQuantity.text = item.quantity.toString()
            tvPrice.text = item.price.toString()
            tvTotalPrice.text = String.format("%.2f",(item.price * item.quantity))
            ibtnDelete.setOnClickListener {
                if(item.quantity > 1){
                    item.quantity--
                    updateItem(item)
                    tvQuantity.text = item.quantity.toString()
                    tvTotalPrice.text = String.format("%.2f",(item.price * item.quantity))
                }else{
                    deleteItem(item)
                }
            }
        }
    }
}