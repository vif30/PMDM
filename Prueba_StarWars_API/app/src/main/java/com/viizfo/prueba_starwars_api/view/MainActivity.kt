package com.viizfo.prueba_starwars_api.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.viizfo.prueba_starwars_api.R
import com.viizfo.prueba_starwars_api.databinding.ActivityMainBinding
import com.viizfo.prueba_starwars_api.viewmodel.NotificationsViewModel


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: ActivityMainBinding

    private val notificationviewmodel: NotificationsViewModel by viewModels()

    private val charactersFragment = CharactersFragment()
    private val moviesFragment = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Change theme after 2 seconds
        Thread.sleep(2000)
        setTheme(R.style.Theme_Prueba_StarWars_API)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationMenu()
        binding.bottomNavigationMenu.selectedItemId = R.id.movies_item
        switchFragment(MoviesFragment())

        setTheme()
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "theme") {
            setTheme()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        when (item.itemId) {
            R.id.menu_play -> {
                Toast.makeText(this, "Play button clicked", Toast.LENGTH_SHORT).show()
                notificationviewmodel.sendNotification()
                return true
            }
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings button clicked", Toast.LENGTH_SHORT).show()
                // Open Settings
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_exit -> {
                Toast.makeText(this, "Exit button clicked", Toast.LENGTH_SHORT).show()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBottomNavigationMenu() {
        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characters_item -> {
                    switchFragment(charactersFragment)
                    true
                }
                R.id.movies_item -> {
                    switchFragment(moviesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun setTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = if (sharedPreferences.getBoolean("theme", false)) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    override fun onDestroy() {
        Glide.get(this).clearMemory()
        super.onDestroy()
    }

    companion object {
        lateinit var context: Context
    }
}