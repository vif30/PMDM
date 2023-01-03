package com.viizfo.p5_shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viizfo.p5_shoppinglist.database.entities.ItemEntity

@Database(entities = arrayOf(ItemEntity::class), version = 1)
abstract class ShoppinglistDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO

    companion object{ //Singleton Pattern
        private var instance:ItemDAO? = null

        fun getInstance(context: Context):ItemDAO{
            return instance ?: Room.databaseBuilder(context, ShoppinglistDatabase::class.java, "items-db").build().itemDao().also { instance = it }
        }
    }
}