package com.viizfo.livedatatrainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.viizfo.livedatatrainer.databinding.FragmentTrainerBinding
import com.viizfo.livedatatrainer.viewmodel.TrainerViewModel


class TrainerFragment : Fragment() {

    private lateinit var binding:FragmentTrainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTrainerBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainerViewModel = ViewModelProvider(this)[TrainerViewModel::class.java]

        trainerViewModel.exerciseLiveData.observe(viewLifecycleOwner){ imageID ->
            binding.ivExercise.setImageResource(imageID)
        }

        trainerViewModel.repetitionLiveData.observe(viewLifecycleOwner) { repetition ->
            if (repetition.equals("CHANGE")) {
                binding.tvChange.visibility = View.VISIBLE
            } else {
                binding.tvChange.visibility = View.GONE
            }
            binding.tvRepetition.text = repetition
        }

    }


}