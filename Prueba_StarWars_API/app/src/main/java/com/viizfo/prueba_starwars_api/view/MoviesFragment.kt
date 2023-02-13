package com.viizfo.prueba_starwars_api.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.viizfo.prueba_starwars_api.R
import com.viizfo.prueba_starwars_api.adapter.StarWarsAdapter
import com.viizfo.prueba_starwars_api.databinding.FragmentMoviesBinding
import com.viizfo.prueba_starwars_api.service.MainRepository
import com.viizfo.prueba_starwars_api.service.StarWarsApiService
import com.viizfo.prueba_starwars_api.service.VMFactory
import com.viizfo.prueba_starwars_api.viewmodel.MainViewModel


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = StarWarsApiService.getRetrofit()
    private val adapter = StarWarsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        binding.svMovies.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), "Buscando $query", Toast.LENGTH_SHORT).show()
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Obtener el valor actual de la preferencia "movie_text"
        val movieText = PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString("movie_text", getString(R.string.tv_movies))

        // Actualizar el texto del TextView con el valor de la preferencia
        binding.tvMovies.text = movieText
    }

    private fun getCharacterById(id: String) {
        viewModel = ViewModelProvider(this, VMFactory(MainRepository(retrofitService)))[MainViewModel::class.java]

        // Bindeo del adaptador
        binding.rvMovies.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            Log.d(tag, "onCreate: $it")
            adapter.setCharacterList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        //viewModel.getCharacterById(id)

    }
}