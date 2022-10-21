package com.cartatar.p2_master_detail_books

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.cartatar.p2_master_detail_books.placeholder.PlaceholderContent
import com.cartatar.p2_master_detail_books.databinding.FragmentItemDetailBinding
import com.cartatar.p2_master_detail_books.model.BookItem
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */


class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var bookItem: BookItem? = null

    lateinit var tvDescription: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                bookItem = it.getString(ARG_ITEM_ID)?.let { id -> BookItem.getBookItemById(id) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        tvDescription = binding.tvDescription


        binding.fab?.setOnClickListener {
            Snackbar.make(it,"Publication date: ${bookItem?.publication_date}", Snackbar.LENGTH_SHORT).show()
        }

        updateContent()

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = bookItem?.title
        binding.appBar?.background = context?.getDrawable(R.drawable.banlibros)



        // Show the placeholder content as text in a TextView.
        bookItem?.let {
            tvDescription.text = it.description
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}