package com.viizfo.p3_navigation_splash_screen.ui.carta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.viizfo.p3_navigation_splash_screen.databinding.FragmentCartaMediodiaBinding


class CartaActivity : Fragment() {

    private var _binding: FragmentCartaMediodiaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCartaMediodiaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMediodia

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}