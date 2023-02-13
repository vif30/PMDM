package com.viizfo.cartas_starwars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.cartas_starwars.adapter.APIAdapter
import com.viizfo.cartas_starwars.databinding.FragmentBottom1Binding
import com.viizfo.cartas_starwars.model.APIResponse
import com.viizfo.cartas_starwars.provider.APIProvider
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Bottom1Fragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private var _binding: FragmentBottom1Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: APIAdapter
    private val dogImages = mutableListOf<APIResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottom1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
        initRecyclerView()
        binding.svDogs.setOnQueryTextListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        adapter = APIAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDogs.adapter = adapter
    }

    private fun searchByName(query:String){
        MainScope().launch {
            val dogList = APIProvider.getCharactersById(query)
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
        Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT) .show()
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