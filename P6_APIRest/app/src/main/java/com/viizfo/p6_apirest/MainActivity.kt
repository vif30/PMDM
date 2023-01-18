package com.viizfo.p6_apirest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.p6_apirest.adapter.SuperHeroAdapter
import com.viizfo.p6_apirest.adapter.SuperHeroProvider
import com.viizfo.p6_apirest.databinding.ActivityMainBinding
import com.viizfo.p6_apirest.model.SuperHero
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/*Application using retrofit connects to the rest api https://superheroapi.com/ and displays
data on the superheroes that the user searches for through the searchview.
As you type the name of the superhero, the app will search for matches in the API, so if you search for
"spider", it will show you all the matches, like "spider-gwen", "scarlet-spider", "spider-woman"...*/
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperHeroAdapter
    private val superHeroList = mutableListOf<SuperHero?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        initRecyclerView()
        binding.svSuperHeroes.setOnQueryTextListener(this)
        binding.svSuperHeroes.setOnCloseListener()
    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter(superHeroList)
        binding.rvSuperHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroes.adapter = adapter
    }
//function to create the options menu
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate (R.menu.my_menu, menu)
        return true
    }
//function to set the listener to the optionsmenu
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.superList -> {
                val url = "https://superheroapi.com/ids.html"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
//Function to search by name a superhero in the API
    private fun searchByName(query: String){
        MainScope().launch {
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
//Function to show with a Toast an error where there is no coincidence in the API
    private fun showError() {
        Toast.makeText(this, "There is no superhero with that name.", Toast.LENGTH_SHORT) .show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }
//Function to collect the data typed by the user and look it up in the api
    override fun onQueryTextChange(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            binding.tvGreet.visibility = View.GONE
            searchByName(query.lowercase())
        } else {
            binding.tvGreet.visibility = View.VISIBLE
            superHeroList.clear()
            adapter.notifyDataSetChanged()
        }
        return true
    }
//Function to reset the recyclerView when the user deletes the SV text
    private fun SearchView.setOnCloseListener() {
        superHeroList.clear()
        adapter.notifyDataSetChanged()
    }
}


