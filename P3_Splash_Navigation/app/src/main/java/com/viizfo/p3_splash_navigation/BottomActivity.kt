package com.viizfo.p3_splash_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.p3_splash_navigation.databinding.ActivityBottomBinding

import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.NavigationUI


class BottomActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityBottomBinding.inflate(layoutInflater).also { binding = it }.root)

       // setSupportActionBar(binding.toolbar);

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!.navController

        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController);
    }
}