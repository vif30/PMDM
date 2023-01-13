package com.viizfo.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.retrofitexample.adapter.DogAdapter
import com.viizfo.retrofitexample.adapter.DogProvider
import com.viizfo.retrofitexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        initRecyclerView()

        binding.svDogs.setOnQueryTextListener(this)

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