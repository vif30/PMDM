package com.viizfo.p5_shoppinglist.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.p5_shoppinglist.R
import com.viizfo.p5_shoppinglist.adapters.ItemAdapter
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity
import com.viizfo.p5_shoppinglist.databinding.ActivityMainBinding
import com.viizfo.p5_shoppinglist.viewmodel.ShoppinglistViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var items: MutableList<ItemEntity> = mutableListOf()
    var total: Double = 0.0
    private lateinit var shoppingListViewModel:ShoppinglistViewModel

    lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
        }.root)

        shoppingListViewModel = ViewModelProvider(this)[ShoppinglistViewModel::class.java]

        shoppingListViewModel.getAllItems()

        shoppingListViewModel.itemListLD.observe(this){
            items.clear()
            items.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
            for(i in items){
                total += i.price * i.quantity
            }
            binding.tvTotal.text =  String.format("%.2f",(total))
        }
        shoppingListViewModel.updateItemLD.observe(this){ itemUpdated ->
            if(itemUpdated == null){
                showMessage("Error updating item")
            }
        }

        shoppingListViewModel.deleteItemLD.observe(this){ id ->
            if(id != -1){
                val item = items.filter {
                    it.id == id
                }[0]
                val pos = items.indexOf(item)
                item.quantity -= 1
                if(item.quantity == 0) {
                    items.removeAt(pos)
                }
                recyclerView.adapter?.notifyItemRemoved(pos)
                total -= (item.quantity * item.price)
                binding.tvTotal.text = total.toString()
            }else{
                showMessage("Error deleting item")
            }
        }

        shoppingListViewModel.insertItemLD.observe(this){
            items.add(it)
            recyclerView.adapter?.notifyItemInserted(items.size)
        }

        binding.fabItem.setOnClickListener {
            openAddActivity()
        }
        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate (R.menu.my_menu, menu)
        return true
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun openAddActivity() {
        val intent = Intent(this, addItemActivity::class.java)
        startActivity(intent)
    }

    fun setUpRecyclerView() {
        adapter = ItemAdapter(items, { itemEntity ->  updateItem(itemEntity) }, { itemEntity -> deleteItem(itemEntity) })
        recyclerView = binding.rvTask
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateItem(itemEntity: ItemEntity) {
        shoppingListViewModel.update(itemEntity)
    }

    private fun deleteItem(itemEntity: ItemEntity) {
        shoppingListViewModel.delete(itemEntity)

    }

}