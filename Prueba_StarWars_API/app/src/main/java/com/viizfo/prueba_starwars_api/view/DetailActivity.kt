package com.viizfo.prueba_starwars_api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import com.viizfo.prueba_starwars_api.databinding.ActivityDetailBinding
import com.viizfo.prueba_starwars_api.model.StarWarsData
import com.viizfo.prueba_starwars_api.viewmodel.MainViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var character: StarWarsData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID del personaje del Intent
        val characterId = intent.getIntExtra("characterId", -1)

        // Obtener los datos del personaje del repositorio
        /*
        viewModel.getCharacterById(characterId).observe(this, Observer {
            character = it

            // Actualizar la interfaz de usuario con los datos del personaje
            binding.tvStarWars.text = character.name
            Picasso.get().load(character.image).into(binding.ivStarWars)
        })*/
    }
}