package com.viizfo.p6_apirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.viizfo.p6_apirest.adapter.SuperHeroAdapter
import com.viizfo.p6_apirest.adapter.SuperHeroProvider
import com.viizfo.p6_apirest.databinding.ActivityMainBinding
import com.viizfo.p6_apirest.model.SuperHero
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperHeroAdapter
    private val superHeroList = mutableListOf<SuperHero?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initRecyclerView()

        binding.svSuperHeroes.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter(superHeroList)
        binding.rvSuperHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroes.adapter = adapter
    }

    /*private fun searchById(query:String){
        MainScope().launch {
            val superheroList: MutableList<SuperHero?> = mutableListOf(SuperHeroProvider.getSuperHeroById(query))
            if(superheroList.isNotEmpty()){
                superHeroList.clear()
                superHeroList.addAll(superheroList)
                adapter.notifyDataSetChanged()
            }else{
                showError()
            }

        }
    }*/
    private fun searchByName(query: String){
        MainScope().launch {
            //val superheroList: MutableList<SuperHero?> = mutableListOf(SuperHeroProvider.getSuperHeroById(query))
            val superheroList: List<SuperHero?> = SuperHeroProvider.getSuperHeroByName(query)
            if(superheroList.isNotEmpty()){
                superHeroList.clear()
                superHeroList.addAll(superheroList)
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
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}