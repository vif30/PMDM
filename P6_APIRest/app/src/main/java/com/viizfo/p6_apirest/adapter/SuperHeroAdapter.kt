package com.viizfo.p6_apirest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.p6_apirest.R
import com.viizfo.p6_apirest.databinding.ItemSuperheroBinding
import com.viizfo.p6_apirest.model.SuperHero


class SuperHeroAdapter(private val superList: MutableList<SuperHero?>) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }
    override fun getItemCount(): Int = superList.size
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superList[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemSuperheroBinding.bind(view)
        fun bind (superhero: SuperHero) {
            //Setting the data from the API in the layout
            Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
            binding.tvName.text = superhero.name
            binding.tvFullName.text = superhero.biography.fullName
            binding.tvPublisher.text = superhero.biography.publisher
            var Aliases = ""
            superhero.biography.aliases.forEach{if(it == superhero.biography.aliases.last()) Aliases += it else Aliases += it + "\n" }
            binding.tvAliases.text = Aliases
            binding.tvFirstApp.text = superhero.biography.firstApp
        }
    }
}