package com.viizfo.p6_api_hp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HPAdapter(private val characters: List<String>) : RecyclerView.Adapter<HPAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }
    override fun getItemCount(): Int = characters.size
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item)
    }

    inner class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemDogBinding.bind(view)
        fun bind (image:String) {
            Picasso.get().load(image).placeholder(R.drawable.loading).into(binding.ivDog)
        }
    }
}