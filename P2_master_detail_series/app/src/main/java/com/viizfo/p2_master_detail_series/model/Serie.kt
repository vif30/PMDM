package com.viizfo.p2_master_detail_series.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import com.viizfo.p2_master_detail_series.R

data class Serie(
    var id:Int,
    var name:String,
    var language:String,
    var genres:Array<String>,
    var status:String,
    var premiered:String,
    var officialSite:String,
    var ratting:Float,
    var image:String,
    var summary:String,
    var gson:Gson
){
    fun getSerieById(id:String):Serie?{
        return SeriesList?.filter { serie ->
            serie.id == id.toInt()
        }?.get(0)
    }

    companion object{
        var SeriesList:MutableList<Serie>? = null
        fun getItemSeries(context:Context):MutableList<Serie>?{
            if(SeriesList==null || SeriesList?.size==0)
                loadItemSeries(context)
            return SeriesList
        }
        private fun loadItemSeries(context: Context){
            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Serie?>?>() {}.type

            val gson = Gson()
            SeriesList = gson.fromJson(rd, listType)
        }
        fun getSerieById(id:String):Serie?{
            return SeriesList?.filter { serie ->
                serie.id == id.toInt()
            }?.get(0)
        }


    }
}
