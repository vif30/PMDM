package com.viizfo.p2_master_detail_series

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.viizfo.p2_master_detail_series.databinding.FragmentItemDetailBinding
import com.viizfo.p2_master_detail_series.model.Serie

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
    private var serie: Serie? = null

    lateinit var tvSummary: TextView
    lateinit var tvGenre: TextView
    lateinit var tvLanguageDetail: TextView
    lateinit var tvPremiered: TextView
    lateinit var tvURL: TextView
    lateinit var extraLayout: LinearLayout
    private var toolbarLayout: CollapsingToolbarLayout? = null
    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                serie = it.getString(ARG_ITEM_ID)?.let { id -> Serie.getSerieById(id) }
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
        tvSummary = binding.tvSummary!!
        tvGenre = binding.tvGenre!!
        tvLanguageDetail = binding.tvLanguage!!
        tvPremiered = binding.tvPremiered!!
        tvURL = binding.tvURL!!
        extraLayout = binding.extraLayout!!

        binding.fab?.setOnClickListener {
            if(extraLayout.isVisible){
                Snackbar.make(it,"Hide extra info?", Snackbar.LENGTH_LONG)
                    .setAction("ACCEPT"){
                        extraLayout.isVisible = false
                    }.show()
            } else {
                Snackbar.make(it,"Press accept for more info", Snackbar.LENGTH_LONG)
                    .setAction("ACCEPT"){
                        extraLayout.isVisible = true
                    }.show()
            }
        }
        updateContent()

        return rootView
    }
    private fun updateContent() {
        toolbarLayout?.title = serie?.name
        binding.appBar?.background = context?.getDrawable(R.drawable.noimage)

        // Show the placeholder content as text in a TextView.
        serie?.let {
            tvSummary.text = it.summary
            binding.appBar?.background = context?.getDrawable(serie!!.image.getImage(requireContext()))
            var generos : String = ""
            for (i in it.genres){
                generos += i + ", "
            }
            tvGenre.text = generos
            tvLanguageDetail.text = it.language
            tvPremiered.text = it.premiered
            tvURL.text = it.officialSite
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