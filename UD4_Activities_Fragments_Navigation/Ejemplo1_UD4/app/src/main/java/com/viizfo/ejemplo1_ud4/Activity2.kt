package com.viizfo.ejemplo1_ud4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.ejemplo1_ud4.MainActivity.Companion.KEY_EXTRA_NAME
import com.viizfo.ejemplo1_ud4.MainActivity.Companion.KEY_EXTRA_RESULT
import com.viizfo.ejemplo1_ud4.MainActivity.Companion.KEY_EXTRA_RESULT_PARCELABLE
import com.viizfo.ejemplo1_ud4.MainActivity.Companion.KEY_EXTRA_SURNAME
import com.viizfo.ejemplo1_ud4.databinding.Activity2Binding


class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //retrieveMainActivityData()
        getParcelableFromIntent()
        binding.btnBack.setOnClickListener{ sendBackName() }
    }
    private fun getParcelableFromIntent() {
        var myText:String? = null

        if(intent.hasExtra(KEY_EXTRA_RESULT_PARCELABLE)){
            val person = intent.getParcelableExtra<Person>(KEY_EXTRA_RESULT_PARCELABLE)
            person.let{person ->
                myText = "${person?.name} ${person?.surname}"
            }
        }

        binding.tvTop.text = myText?:"No user"
    }

    private fun sendBackName() {
        val text = binding.etName.text.toString()
        val returnIntent = Intent().apply {
            putExtra(KEY_EXTRA_RESULT, text)
        }   //Creates a new Intent with editText content as an extra

        if(text != "")
            setResult(RESULT_OK, returnIntent) //The action went ok.
        else
            setResult(RESULT_CANCELED, returnIntent)

        finish() //Finish and close this activity
    }

    private fun openActivity2() {
        finish()
    }
    private fun retrieveMainActivityData() {
        var name:String? = null

        if(intent.hasExtra(KEY_EXTRA_NAME))
            name = "${intent.getStringExtra(KEY_EXTRA_NAME).toString()}"

        if(intent.hasExtra(KEY_EXTRA_SURNAME))
            name = "${name?:""} ${intent.getStringExtra(KEY_EXTRA_SURNAME).toString()}"

        binding.tvTop.text = name ?: "No user"
    }
}