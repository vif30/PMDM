package com.viizfo.api

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.api.databinding.ItemDogBinding

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)
    fun bind (image: DogsResponse) {
        Picasso.get().load(image.image).into(binding.ivDog)
    }
}