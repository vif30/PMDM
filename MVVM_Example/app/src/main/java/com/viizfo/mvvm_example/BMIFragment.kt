package com.viizfo.mvvm_example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.viizfo.mvvm_example.databinding.FragmentBMIBinding
import com.viizfo.mvvm_example.BMICalculator
import com.viizfo.mvvm_example.BMICalculatorViewModel
import java.text.DecimalFormat
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBMIBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentBMIBinding
            .inflate(inflater, container, false)
            .also { binding = it }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.btnCalculate.setOnClickListener {


            binding.progressCircular.visibility = View.VISIBLE

            val bmiCalculator = BMICalculator()
            val mHeight = binding.etHeight.text.toString().toDouble()
            val mWeight = binding.etWeight.text.toString().toDouble()

            val bmi = bmiCalculator.calculate(BMICalculator.Request(mWeight, mHeight))
            val dec = DecimalFormat("#,###.00")

            binding.tvBMI.text = dec.format(bmi).toString()
            binding.progressCircular.visibility = View.GONE
        }*/

        //Obtaining a reference to our ViewModel
        val bmiCalculatorViewModel:BMICalculatorViewModel = ViewModelProvider(this)[BMICalculatorViewModel::class.java]
        binding.btnCalculate.setOnClickListener {

            closeKeyBoard(it) //Closing soft keyboard

            var error = false
            var mHeight = 0.0
            var mWeight = 0.0

            binding.etHeight.error = null
            binding.etWeight.error = null
            try {
                mHeight = binding.etHeight.text.toString().toDouble()
            }catch (e : Exception){
                binding.etHeight.error = "You should insert a number"
                error = true
            }

            try {
                mWeight = binding.etWeight.text.toString().toDouble()
            }catch (e : Exception){
                binding.etWeight.error = "You should insert a number"
                error = true
            }

            if(!error){
                //Call to calculator viewModel
                bmiCalculatorViewModel.calculateBMI(mWeight,mHeight)
            }


        }

        //keep observing change on mbi from viewModel
        bmiCalculatorViewModel.bmi.observe(viewLifecycleOwner){ newBMI ->
            val dec = DecimalFormat("#,###.00")

            binding.tvBMI.text = dec.format(newBMI).toString()
            binding.etWeight.error = null
            binding.etHeight.error = null
        }

        bmiCalculatorViewModel.weightError.observe(viewLifecycleOwner){ error ->
            if(error != "") {
                binding.tvBMI.text  = ""
                binding.etWeight.error = error
            }else
                binding.etWeight.error = null

        }
        bmiCalculatorViewModel.heightError.observe(viewLifecycleOwner){ error ->

            if(error != "") {
                binding.tvBMI.text  = ""
                binding.etHeight.error = error
            }else
                binding.etHeight.error = null




        }
        bmiCalculatorViewModel.error.observe(viewLifecycleOwner){ error ->
            if(error != "")
                binding.tvBMI.text = error


        }

        bmiCalculatorViewModel.loading.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading)
                binding.progressCircular.visibility =  View.VISIBLE
            else
                binding.progressCircular.visibility =  View.GONE
        }
    }

    private fun closeKeyBoard(view:View){
        (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}