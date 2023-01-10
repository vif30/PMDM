package com.viizfo.p5_shoppinglist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.viizfo.p5_shoppinglist.databinding.ActivityAddItemBinding
import com.viizfo.p5_shoppinglist.viewmodel.ShoppinglistViewModel

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private lateinit var shoppingListViewModel:ShoppinglistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityAddItemBinding.inflate(layoutInflater).also {
            binding = it
        }.root)

        shoppingListViewModel = ViewModelProvider(this)[ShoppinglistViewModel::class.java]
        shoppingListViewModel.getAllItems()
        shoppingListViewModel.updateItemLD.observe(this){ itemUpdated ->
            if(itemUpdated == null){
                showMessage("Error updating item")
            }
        }
        binding.btnAdd.setOnClickListener{
            addItem()
            openMainActivity()
        }
    }
    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
    private fun addItem() {
        shoppingListViewModel.add(binding.eTName.text.toString(), binding.eTQuantity.text.toString().toInt(), binding.etPrice.text.toString().toDouble())
    }

}