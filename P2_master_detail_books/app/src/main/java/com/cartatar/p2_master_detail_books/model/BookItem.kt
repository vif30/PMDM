package com.cartatar.p2_master_detail_books.model

import android.content.Context
import com.cartatar.p2_master_detail_books.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class BookItem(
    val id:String,
    val author:String,
    val description:String,
    val publication_date:String,
    val title:String,
    val url_image:String,
){
    companion object{
        private var booksList:MutableList<BookItem>? = null
        fun getItemBooks(context:Context):MutableList<BookItem>?{
            if(booksList==null || booksList?.size==0)
                loadItemBooks(context)

            return booksList
        }

        private fun loadItemBooks(context: Context){
            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<BookItem?>?>() {}.type

            val gson = Gson()
            booksList = gson.fromJson(rd, listType)
        }

        fun getBookItemById(id:String):BookItem?{
            return booksList?.filter { book ->
                book.id == id
            }?.get(0)
        }

    }
}