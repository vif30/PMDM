package com.viizfo.posibleexamen2.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viizfo.posibleexamen2.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class Question(
    var id:Int,
    var question:String,
    var answers: Array<String>,
    var image: String,
    var correct_answer: Inta
){
    fun getQuestionById(id:String):Question?{     //Function to filter series by id
        return QuestionsList?.filter { question ->
            question.id == id.toInt()
        }?.get(0)
    }

    companion object{
        private var QuestionsList:MutableList<Question>? = null   //List of series
        fun getItemQuestion(context: Context):MutableList<Question>?{     //Function that returns the list of series
            if(QuestionsList==null || QuestionsList?.size==0)
                loadItemSeries(context)
            return QuestionsList
        }
        private fun loadItemSeries(context: Context){   //Function to load data from JSON file
            val raw = context.resources.openRawResource(R.raw.test)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Question?>?>() {}.type

            val gson = Gson()
            QuestionsList = gson.fromJson(rd, listType)
        }
        fun getQuestionById(id:String):Question?{     //Function to filter series by id
            return QuestionsList?.filter { question ->
                question.id == id.toInt()
            }?.get(0)
        }
    }
}
