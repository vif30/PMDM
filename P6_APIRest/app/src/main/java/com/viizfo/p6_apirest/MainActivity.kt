package com.viizfo.p6_apirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.p6_apirest.adapter.SuperHeroAdapter
import com.viizfo.p6_apirest.adapter.SuperHeroProvider
import com.viizfo.p6_apirest.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperHeroAdapter
    private val superHeroImage = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initRecyclerView()

        binding.svSuperHeroes.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter(superHeroImage)
        binding.rvSuperHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroes.adapter = adapter
    }

    private fun searchById(query:String){
        MainScope().launch {
            val superheroList = SuperHeroProvider.getSuperHeroById(query)
            if(superheroList.isNotEmpty()){
                superHeroImage.clear()
                superHeroImage.addAll(superheroList)
                adapter.notifyDataSetChanged()
            }else{
                showError()
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT) .show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchById(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}