package com.viizfo.p2_master_detail_series.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import android.content.Context
import com.google.gson.JsonArray
import org.json.JSONTokener
import raw.datos_json
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

data class Serie(
    var id:Int,
    var name:String,
    var languaje:String,
    var genres:Array<String>,
    var status:String,
    var premiered:String,
    var officialSite:String,
    var ratting:Float,
    var image:String,
    var summary:String,
    var gson:Gson
){
    companion object{
        var SerieList:MutableList<Serie> = ArrayList()
        @JvmName("getSerieList1")
        fun getSerieList(): MutableList<Serie>{
            SerieList.clear()
            val raw = context.resources.openRawResource(datos_json)  //Be aware that we need the activity context
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Serie?>?>() {}.type

            val gson = Gson()

            serieList.addAll(gson.fromJson(rd, listType))
            return serieList
        }

    }
}
