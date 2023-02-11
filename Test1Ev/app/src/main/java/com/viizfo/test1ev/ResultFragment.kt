package com.viizfo.test1ev

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.viizfo.test1ev.databinding.FragmentResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val info = args.resultInfo
        binding.tvResult.text = getString(R.string.result_questionnaire,info.numCorrects, info.totalQuestions)

        CoroutineScope(Dispatchers.Main).launch{
            delay(2000)
            val action = ResultFragmentDirections.actionResultFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

}