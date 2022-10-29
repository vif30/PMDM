package com.viizfo.navigationexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.navigationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        setSupportActionBar(binding.toolbar)
        binding.btnGotoDrawerActivity.setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))
        }
        binding.btnGotoBottomActivity.setOnClickListener {
            startActivity(Intent(this, BottomActivity::class.java))
        }
        binding.btnGotoOptionsActivity.setOnClickListener {
            startActivity(Intent(this, OptionsActivity::class.java))
        }
        binding.btnGotoTabbedActivity.setOnClickListener {
            startActivity(Intent(this, TabbedActivity::class.java))
        }
    }
}