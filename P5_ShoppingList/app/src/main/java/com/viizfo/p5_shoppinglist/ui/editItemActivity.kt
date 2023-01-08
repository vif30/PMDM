package com.viizfo.p5_shoppinglist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.viizfo.p5_shoppinglist.adapters.ItemAdapter
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity

import com.viizfo.p5_shoppinglist.databinding.ActivityEditItemBinding
import com.viizfo.p5_shoppinglist.viewmodel.ShoppinglistViewModel

class editItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding
    var items: MutableList<ItemEntity> = mutableListOf()
    private lateinit var shoppingListViewModel: ShoppinglistViewModel
    lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityEditItemBinding.inflate(layoutInflater).also {
            binding = it
        }.root)
        shoppingListViewModel = ViewModelProvider(this)[ShoppinglistViewModel::class.java]
        shoppingListViewModel.getAllItems()
        shoppingListViewModel.updateItemLD.observe(this){ itemUpdated ->

        }
        binding.btnEdit.setOnClickListener{
            openMainActivity()
        }
    }
    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun editItem(item: ItemEntity) {
        val newItem = item.copy(quantity = binding.eTQuantity.text.toString().toInt(), price = binding.etPrice.text.toString().toDouble())
        updateItem(newItem)
    }
    private fun updateItem(itemEntity: ItemEntity) {
        shoppingListViewModel.update(itemEntity)
    }

}