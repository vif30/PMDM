package com.viizfo.test1ev.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viizfo.test1ev.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class Questionnaire(
    val questions: MutableList<Question>
){
    companion object{
        suspend fun loadQuestionnaire(context:Context): Questionnaire = withContext(Dispatchers.IO){
            val raw = context.resources.openRawResource(R.raw.test)
            val rd = BufferedReader(InputStreamReader(raw))
            val listType: Type = object : TypeToken<MutableList<Question?>?>() {}.type
            val gson = Gson()
            Questionnaire(gson.fromJson(rd, listType))
        }
    }
}