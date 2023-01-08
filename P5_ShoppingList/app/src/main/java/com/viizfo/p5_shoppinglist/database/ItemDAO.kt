package com.viizfo.p5_shoppinglist.database

import androidx.room.*
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity

@Dao
interface ItemDAO:MyDao {
    @Query("SELECT * FROM item_entity")
    override fun getAllItems(): MutableList<ItemEntity>

    @Insert
    override fun addItem(itemEntity : ItemEntity):Long

    @Query("SELECT * FROM item_entity WHERE id LIKE :id")
    override fun getItemById(id: Long): ItemEntity

    @Update
    override fun updateItem(itemEntity: ItemEntity):Int

    @Delete
    override fun deleteItem(itemEntity: ItemEntity):Int
}