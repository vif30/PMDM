package com.viizfo.p4_livedata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.viizfo.p4_livedata.R
import com.viizfo.p4_livedata.model.Language

class LanguageViewModel(application: Application) : AndroidViewModel(application) {
    private var language: Language = Language()
    var languageLiveData : LiveData<Int>
    var langNameLiveData : LiveData<String>
    private var previousLanguage:String =""
    init {
        languageLiveData = Transformations.switchMap(language.orderLiveData,){
                progLang ->
            val mprogLang = progLang.split(":")[0]
            if(mprogLang != previousLanguage){          //we select the correct image for every language
                previousLanguage = mprogLang
                var imageID:Int = when(mprogLang){
                    "LANGUAGE1" -> R.drawable.cplus
                    "LANGUAGE2" -> R.drawable.csharp
                    "LANGUAGE3" -> R.drawable.java
                    "LANGUAGE4" -> R.drawable.javascript
                    "LANGUAGE5" -> R.drawable.kotlin
                    "LANGUAGE6" -> R.drawable.python
                    "LANGUAGE7" -> R.drawable.typescript
                    else -> R.drawable.cplus
                }
                return@switchMap MutableLiveData<Int>(imageID)
            }
            return@switchMap null
        }
        langNameLiveData = Transformations.switchMap(language.orderLiveData) { exercise ->
            return@switchMap MutableLiveData<String>(exercise.split(":")[0])    //we send the language "id" to the language fragment
        }
    }
}