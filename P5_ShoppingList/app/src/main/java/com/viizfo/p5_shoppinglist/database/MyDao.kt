package com.viizfo.p5_shoppinglist.database

import com.viizfo.p5_shoppinglist.database.entities.ItemEntity

interface MyDao {
    fun getAllItems(): MutableList<ItemEntity>

    fun addItem(itemEntity: ItemEntity):Long //Id of the new item

    fun getItemById(id: Long): ItemEntity

    fun updateItem(itemEntity: ItemEntity):Int //Number of affected rows

    fun deleteItem(itemEntity: ItemEntity):Int //Number of affected rows
}