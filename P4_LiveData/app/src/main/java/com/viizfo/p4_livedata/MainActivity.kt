package com.viizfo.p4_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Application that displays on the screen the name and logo of a
//random programming language, which changes randomly every second

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}