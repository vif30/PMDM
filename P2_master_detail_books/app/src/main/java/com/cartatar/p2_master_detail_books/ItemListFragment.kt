package com.cartatar.p2_master_detail_books

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cartatar.p2_master_detail_books.placeholder.PlaceholderContent;
import com.cartatar.p2_master_detail_books.databinding.FragmentItemListBinding
import com.cartatar.p2_master_detail_books.databinding.ItemListContentBinding
import com.cartatar.p2_master_detail_books.model.BookItem
import com.google.android.material.snackbar.Snackbar



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


        /** Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView ->
            val bookItem = itemView.tag as BookItem
            val bundle = Bundle()
            bundle.putString(
                ItemDetailFragment.ARG_ITEM_ID,
                bookItem.id
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



        binding.fabLoad.setOnClickListener {
            Snackbar.make(it,"Do you want to load data?", Snackbar.LENGTH_LONG)
                .setAction("Load"){
                    setupRecyclerView(recyclerView, onClickListener)
                    binding.tvWarn.visibility = View.GONE
                    binding.fabLoad.visibility = View.GONE
                }.show()

        }

    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            BookItem.getItemBooks(context?:recyclerView.context),
            onClickListener,
            context?:recyclerView.context
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: MutableList<BookItem>?,
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
            val bookItem = values!![position]
            holder.title.text = bookItem.title
            holder.author.text = bookItem.author
            holder.coverPage.setImageResource(bookItem.url_image.toIntId(context))

            with(holder.itemView) {
                tag = bookItem
                setOnClickListener(onClickListener)

            }
        }

        override fun getItemCount() = values?.size?:0

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val author: TextView = binding.tvAuthor
            val title: TextView = binding.tvTitle
            val coverPage: ImageView = binding.ivPoster
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}