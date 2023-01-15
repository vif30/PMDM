package com.viizfo.p6_apirest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.p6_apirest.R
import com.viizfo.p6_apirest.databinding.ItemSuperheroBinding


class SuperHeroAdapter(private val image: List<String>) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }
    override fun getItemCount(): Int = image.size
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = image[position]
        holder.bind(item)
    }

    inner class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemSuperheroBinding.bind(view)
        fun bind (image: String) {
            /*Glide.with(context)
                .load(image)
                .placeholder(R.raw.loading) //Image that will be displayed while loading the image to be displayed
                .error(ERROR_IMAGE) //Image that we will show if something goes wrong. it is typically use a drawable image stored
                .into(binding.ivSuperhero) //ImageView that will contain the image*/
            //Picasso.get().load(image).into(binding.ivSuperhero)

            //binding.tvId.text = id
            binding.tvName.text = image
        }

    }

}