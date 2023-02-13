package com.viizfo.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.api.databinding.ItemDogBinding

class DogAdapter(private val superList: MutableList<DogsResponse>) : RecyclerView.Adapter<DogAdapter.SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }
    override fun getItemCount(): Int = superList.size
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superList[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemDogBinding.bind(view)
        fun bind (superhero: DogsResponse) {
            //Setting the data from the API in the layout
            Picasso.get().load(superhero.image).into(binding.ivDog)
        }
    }
}