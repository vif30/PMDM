package com.viizfo.posibleexamen.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import com.viizfo.posibleexamen.R

data class Serie(
    //Class variables
    var id:Int,
    var name:String,
    var language:String,
    var genres:Array<String>,
    var status:String,
    var premiered:String,
    var officialSite:String,
    var rating:Float,
    var image:String,
    var summary:String,
    var gson:Gson
){
    companion object{
        private var SeriesList:MutableList<Serie>? = null   //List of series
        fun getItemSeries(context:Context):MutableList<Serie>?{     //Function that returns the list of series
            if(SeriesList==null || SeriesList?.size==0)
                loadItemSeries(context)
            return SeriesList
        }
        private fun loadItemSeries(context: Context){   //Function to load data from JSON file
            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Serie?>?>() {}.type

            val gson = Gson()
            SeriesList = gson.fromJson(rd, listType)
        }
        fun getSerieById(id:String):Serie?{     //Function to filter series by id
            return SeriesList?.filter { serie ->
                serie.id == id.toInt()
            }?.get(0)
        }
    }
}
