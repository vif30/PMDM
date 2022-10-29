package com.viizfo.p3_navigation_splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_P3_Navigation_Splash)
        Thread.sleep(3000)
        setContentView(R.layout.activity_main)


    }
}