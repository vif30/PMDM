package com.viizfo.p5_shoppinglist.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viizfo.p5_shoppinglist.R
import com.viizfo.p5_shoppinglist.adapters.ItemAdapter
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity
import com.viizfo.p5_shoppinglist.databinding.ActivityMainBinding
import com.viizfo.p5_shoppinglist.viewmodel.ShoppinglistViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private var items: MutableList<ItemEntity> = mutableListOf()
    private lateinit var shoppingListViewModel:ShoppinglistViewModel

    private lateinit var adapter: ItemAdapter

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
            binding.tvTotal.text =  calculateTotal()
        }
        shoppingListViewModel.updateItemLD.observe(this){ itemUpdated ->
            if(itemUpdated == null){
                showMessage("Error updating item")
            }
            else{
                binding.tvTotal.text = calculateTotal()
            }
        }
        shoppingListViewModel.deleteItemLD.observe(this){ id ->
            if(id != -1){
                val item = items.filter {
                    it.id == id
                }[0]
                val pos = items.indexOf(item)
                items.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
                binding.tvTotal.text = calculateTotal()
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

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.delete -> {
                deleteAll()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.backup -> {
                createBackup()
            }
            R.id.restore -> {
                deleteAll()
                restoreBackup()
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun deleteAll(){
        val itr = items.iterator()
        while (itr.hasNext()){
            deleteItem(itr.next())
        }
        items.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun createBackup(){
        val gson = Gson()
        val jsonItemList: String = gson.toJson(items)
        this.openFileOutput("backup.json", Context.MODE_PRIVATE).use{
            it.write(jsonItemList.toByteArray())
        }
        showMessage("Backup created successfully!")
    }

    private fun restoreBackup(){
        val backup = this.openFileInput("backup.json")
        val rd = BufferedReader(InputStreamReader(backup))
        val listType: Type = object  : TypeToken<MutableList<ItemEntity?>?>(){}.type
        val gson = Gson()
        items = gson.fromJson(rd, listType)
        val itr = items.iterator()
        while (itr.hasNext()){
            shoppingListViewModel.add(itr.next().name,itr.next().quantity,itr.next().price)
        }
        recyclerView.adapter?.notifyDataSetChanged()
        binding.tvTotal.text =  calculateTotal()
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

     private fun openAddActivity() {
        val intent = Intent(this, addItemActivity::class.java)
        startActivity(intent)
    }

    private fun setUpRecyclerView() {
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

    private fun calculateTotal(): String {
        var total = 0.0
        for(i in items){
            total += i.price * i.quantity
        }
        return String.format("%.2f",(total))
    }
    
}


