package com.viizfo.masterdetailexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.masterdetailexample.databinding.ListItemBinding
import com.viizfo.masterdetailexample.model.SuperHero

class MyItemRecyclerViewAdapter(
    private val superHeroList: List<SuperHero>,
    private val listener:OnItemClick?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    //Creates a ViewHolder for every item of the list (superHeroList)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    //In this function customize our ViewHolder with its data depending on the position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val superhero = superHeroList[position]
        holder.tvSuperhero.text = superhero.SuperHero
        holder.tvRealName.text = superhero.realName
        holder.tvPublisher.text = superhero.publisher


        //We are using an extension toBipmap
        holder.ivPoster.setImageBitmap(superhero.photo.toBitmap(holder.ivPoster.context))

        //We set the tag with superhero data, in order to obtain its data in the listener
        holder.itemView.tag = superhero

        holder.itemView.setOnClickListener(holder) //Our ViewHolder implements OnClickListener
    }

    //We need to know the length of the list. It will be the size of out data list
    override fun getItemCount(): Int = superHeroList.size

    //This inner class contains all view of each item on list_item.xml
    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val tvSuperhero: TextView = binding.tvSuperhero
        val tvRealName: TextView = binding.tvRealName
        val tvPublisher: TextView = binding.tvPublisher
        val ivPoster: ImageView = binding.ivPoster

        override fun toString(): String {
            return super.toString() + " ${tvRealName.text} is ${tvSuperhero.text} from ${tvPublisher.text}"
        }

        override fun onClick(v: View?) {

            //First we recovery de superHero through the tag. Remember we have set it before (onBindViewHolder)
            val superHero = v?.tag as SuperHero

            //later we call our callback with the SuperHero param to inform ListFragment, and then MainActivity
            listener?.onItemClick(superHero)
        }
    }

}