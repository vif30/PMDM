package com.viizfo.ejemplo1_ud4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.viizfo.ejemplo1_ud4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val userName:String = "Vicente"
    private val surName:String = "Izquierdo"

    companion object{ //A companion objects acts like an static class in Java
        val KEY_EXTRA_NAME:String ="MY_KEY_EXTRA_NAME"
        val KEY_EXTRA_SURNAME:String ="MY_KEY_EXTRA_SURNAME"
        val KEY_EXTRA_RESULT:String ="MY_KEY_EXTRA_RESULT"
        val KEY_EXTRA_RESULT_PARCELABLE:String ="MY_KEY_EXTRA_RESULT_PARCELABLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoActivity2.setOnClickListener{ openSomeActivityForResult() }
    }

    private fun sendParcelablePerson(){
        val person = Person("Vicente", "Izquierdo")
        val intent = Intent(this, Activity2::class.java).apply{
            putExtra(KEY_EXTRA_RESULT_PARCELABLE, person)
        }
        startActivity(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            binding.tvGreeting.text = "Hello ${data?.getStringExtra(KEY_EXTRA_RESULT)?:"No return"}"
        }
    }

    fun openSomeActivityForResult() {
        val intent = Intent(this, Activity2::class.java)
        resultLauncher.launch(intent)
    }

    private fun openActivity2() {
        val intent = Intent(this, Activity2::class.java).apply {
            putExtra(KEY_EXTRA_NAME, userName)
            putExtra(KEY_EXTRA_SURNAME, surName)
        }
        startActivity(intent)
    }
}