package com.viizfo.prueba_starwars_api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viizfo.prueba_starwars_api.databinding.StarwarsItemBinding
import com.viizfo.prueba_starwars_api.model.StarWarsData

class StarWarsAdapter: RecyclerView.Adapter<StarWarsAdapter.MainViewHolder>() {

    // Lista de personajes
    var starWarsList = listOf<StarWarsData>()

    // Función que actualiza la lista de personajes
    fun setCharacterList (characterList: List<StarWarsData>){
        starWarsList = characterList.toMutableList()
        notifyDataSetChanged()
    }

    // Función que infla el layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = StarwarsItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    // Función que devuelve el tamaño de la lista
    override fun getItemCount(): Int = starWarsList.size

    // Función que enlaza los datos con la vista
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = starWarsList[position]

        holder.binding.tvStarWars.text = character.name
        Picasso.get().load(character.image).into(holder.binding.ivStarWars)

        // OnclickListener para cada elemento de la lista
        /*
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, character.name, Toast.LENGTH_SHORT).show()

            val bundle = Bundle()
            bundle.putParcelable("character", character)
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)


        }*/

        /*Glide.with(holder.itemView)
            .load(character.image)
            .into(holder.binding.ivStarWars)*/
    }

    // Clase que representa a cada elemento de la lista
    class MainViewHolder(val binding: StarwarsItemBinding) : RecyclerView.ViewHolder(binding.root)

}