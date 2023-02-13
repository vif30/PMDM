package com.viizfo.cartas_starwars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.cartas_starwars.R
import com.viizfo.cartas_starwars.databinding.ItemBinding
import com.viizfo.cartas_starwars.model.APIResponse

class APIAdapter(private val characterList: MutableList<APIResponse>) : RecyclerView.Adapter<APIAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }
    override fun getItemCount(): Int = characterList.size
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterList[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)
        fun bind (personaje: APIResponse) {
            //Setting the data from the API in the layout
            Picasso.get().load(personaje.image).into(binding.ivPersonaje)
        }
    }
}