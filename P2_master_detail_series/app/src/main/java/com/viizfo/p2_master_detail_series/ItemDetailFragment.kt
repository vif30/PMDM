package com.viizfo.p2_master_detail_series

import android.annotation.SuppressLint
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
// Fragment that loads data of the clicked serie
class ItemDetailFragment : Fragment() {

    private var serie: Serie? = null
    private lateinit var tvSummary: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvLanguageDetail: TextView
    private lateinit var tvPremiered: TextView
    private lateinit var tvURL: TextView
    private lateinit var extraLayout: LinearLayout
    private var toolbarLayout: CollapsingToolbarLayout? = null
    private var _binding: FragmentItemDetailBinding? = null

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
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //we initialize the elements of the layout
        toolbarLayout = binding.toolbarLayout
        tvSummary = binding.tvSummary
        tvGenre = binding.tvGenre
        tvLanguageDetail = binding.tvLanguage
        tvPremiered = binding.tvPremiered
        tvURL = binding.tvURL
        extraLayout = binding.extraLayout

        binding.faButton.setOnClickListener {      //Listener for the Floating Action Button
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
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateContent() {   //Function to load data to the app layout
        toolbarLayout?.title = serie?.name
        binding.appBar.background = context?.getDrawable(R.drawable.noimage)

        // Show the placeholder content as text in a TextView.
        serie?.let {
            tvSummary.text = it.summary
            binding.appBar.background = context?.getDrawable(serie!!.image.getImage(requireContext()))
            var generos = ""
            for (i in it.genres){
                generos += "$i, "
            }
            tvGenre.text = generos
            tvLanguageDetail.text = it.language
            tvPremiered.text = it.premiered
            tvURL.text = it.officialSite
        }
    }
    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}