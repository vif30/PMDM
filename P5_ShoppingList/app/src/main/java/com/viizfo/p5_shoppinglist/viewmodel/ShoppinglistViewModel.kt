package com.viizfo.p5_shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.viizfo.p5_shoppinglist.database.MyDao
import com.viizfo.p5_shoppinglist.database.ShoppinglistDatabase
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppinglistViewModel(application: Application): AndroidViewModel(application) {
    private val context = application
    private var myDao:MyDao = ShoppinglistDatabase.getInstance(context)

    val itemListLD:MutableLiveData<MutableList<ItemEntity>> = MutableLiveData()
    val updateItemLD:MutableLiveData<ItemEntity?> = MutableLiveData()
    val deleteItemLD:MutableLiveData<Int> = MutableLiveData()
    val insertItemLD:MutableLiveData<ItemEntity> = MutableLiveData()

    fun getAllItems(){
        viewModelScope.launch(Dispatchers.IO) {
            itemListLD.postValue(myDao.getAllItems())
        }
    }
    fun add(name:String, quantity: Int, price: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = myDao.addItem(ItemEntity(name = name, quantity = quantity, price = price))
            val recoveryItem = myDao.getItemById(id)
            insertItemLD.postValue(recoveryItem)
        }
    }
    fun delete(item:ItemEntity){
        viewModelScope.launch(Dispatchers.IO) {
            val res = myDao.deleteItem(item)
            if(res>0)
                deleteItemLD.postValue(item.id)
            else{
                deleteItemLD.postValue(-1)
            }
        }
    }
    fun update(item:ItemEntity){
        viewModelScope.launch(Dispatchers.IO) {
            val res = myDao.updateItem(item)
            if(res>0)
                updateItemLD.postValue(item)
            else
                updateItemLD.postValue(null)
        }
    }

}