package com.viizfo.api


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.api.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<DogsResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initRecyclerView()

        binding.svDogs.setOnQueryTextListener(this)
        binding.button.setOnClickListener { searchAll() }

    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }



    private fun searchByName(query:String){
        MainScope().launch {
            val dogList = DogProvider.getDogsByBreed(query)
            if(dogList.isNotEmpty()){
                dogImages.clear()
                dogImages.addAll(dogList)
                adapter.notifyDataSetChanged()
            }else{
                showError()
            }
        }
    }
    private fun searchAll(){
        MainScope().launch {
            val dogList = DogProvider.getAllDogs("all")
            if(dogList.isNotEmpty()){
                dogImages.addAll(dogList)
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