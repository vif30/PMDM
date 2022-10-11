package com.viizfo.clickscounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.viizfo.clickscounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var counter = 0     //creates a initial counter variable

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.increaseCounter.setOnClickListener(){
            counter++   //increases the counter variable +1
            binding.clicksCounter.text = "You have clicked $counter times"  //Changes the clicksCounter TextView value
        }

    }
}