package com.viizfo.prueba_starwars_api.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.viizfo.prueba_starwars_api.adapter.StarWarsAdapter
import com.viizfo.prueba_starwars_api.databinding.FragmentCharactersBinding
import com.viizfo.prueba_starwars_api.service.MainRepository
import com.viizfo.prueba_starwars_api.service.StarWarsApiService
import com.viizfo.prueba_starwars_api.service.VMFactory
import com.viizfo.prueba_starwars_api.viewmodel.MainViewModel


class CharactersFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = StarWarsApiService.getRetrofit()
    private val adapter = StarWarsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Cargar imágenes en bajo rendimiento (por si la imagen es demasiado grande)
        Glide.get(requireContext()).setMemoryCategory(MemoryCategory.LOW)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvCharacters
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        getCharacters()
    }

    // Solicitar lista de personajes y observar cambios
    private fun getCharacters() {
        viewModel = ViewModelProvider(this, VMFactory(MainRepository(retrofitService)))[MainViewModel::class.java]

        // Bindeo del adaptador
        binding.rvCharacters.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            Log.d(tag, "onCreate: $it")
            adapter.setCharacterList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getAllCharacters()

    }
}