package com.viizfo.p2_master_detail_series
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


        binding.fab?.setOnClickListener {
            Snackbar.make(it,"Publication date: ${serie?.genres}", Snackbar.LENGTH_SHORT).show()
        }

        updateContent()

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = serie?.name
        binding.appBar?.background = context?.getDrawable(R.drawable.glee)



        // Show the placeholder content as text in a TextView.
        serie?.let {
            tvSummary.text = it.summary

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
