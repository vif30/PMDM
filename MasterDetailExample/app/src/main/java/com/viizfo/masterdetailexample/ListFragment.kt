package com.viizfo.masterdetailexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.masterdetailexample.model.SuperHero

class ListFragment() : Fragment() {

    //property that indicates if we want to view a single item list or grid list. Used on onCreateView
    private var columnCount = 2

    //Listener is our callback
    var listener:OnItemClick? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //When fragment is created, "search" on its arguments a bundle with Key ARG_COLUMN_COUNT and set the property above
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

    }

    //When fragment is attaching to an activity, the context is the activity. Remember MainActivity Implements OnItemclick
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClick) {
            listener = context
        }
    }

    //Detach the listener. Set it to null
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate creates a View variable from a layout resource, in this case fragment_list which is just a RecyclerView
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set the adapter and the LayoutManager of the recyclerView
        if (view is RecyclerView) { //smart cast
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(SuperHero.getSuperHeroes(),listener)
            }
        }
        return view
    }

    //Creating an static method to simulate factory pattern. Creates a new ListFragment, and sets countColumn param through bundle.
    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}