package com.viizfo.p2_master_detail_series

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color.parseColor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.p2_master_detail_series.databinding.FragmentItemListBinding
import com.viizfo.p2_master_detail_series.databinding.ItemListContentBinding
import com.viizfo.p2_master_detail_series.model.Serie
import com.viizfo.p2_master_detail_series.placeholder.PlaceholderContent


class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)
        val onClickListener = View.OnClickListener { itemView ->
            val Serie = itemView.tag as Serie
            val bundle = Bundle()
            bundle.putString(
                ItemDetailFragment.ARG_ITEM_ID,
                Serie.id.toString()
            )
            if (itemDetailFragmentContainer != null) {
                //We are in a wide device (sw-600dp)
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                //we are in a normal device.
                itemView.findNavController().navigate(R.id.show_item_detail, bundle)
            }
        }
        setupRecyclerView(recyclerView, onClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            Serie.getItemSeries(context?:recyclerView.context),
            onClickListener,
            context?:recyclerView.context
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: MutableList<Serie>?,
        private val onClickListener: View.OnClickListener,
        private val context: Context
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val serie = values!![position]
            holder.name.text = serie.name
            holder.language.text = serie.language
            holder.imageSerie.setImageResource(serie.image.getImage(context))
            if(serie.status == "Ended"){
                holder.card.setCardBackgroundColor(parseColor("#6a1b9a"))
            } else {
                holder.card.setCardBackgroundColor(parseColor("#a569bd"))
            }
            with(holder.itemView) {
                tag = serie
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values?.size?:0

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val name: TextView = binding.tvName
            val language: TextView = binding.tvLanguageDetail
            val imageSerie: ImageView = binding.ivSerie
            val card: CardView = binding.card
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}