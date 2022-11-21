package com.viizfo.posibleexamen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.viizfo.posibleexamen2.databinding.FragmentQuestionnaireBinding
import com.viizfo.posibleexamen2.model.Question

class questionnaireFragment : Fragment() {
    private var _binding: FragmentQuestionnaireBinding? = null
    private val binding get() = _binding!!
    private var question: Question? = null
    private lateinit var ivQuestion: ImageView
    private lateinit var  tvQuestion: TextView
    private lateinit var rgQuestion: RadioGroup
    private lateinit var rb1: RadioButton
    private lateinit var rb2: RadioButton
    private lateinit var rb3: RadioButton
    private lateinit var rb4: RadioButton
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                question = it.getString(ARG_ITEM_ID)?.let { id -> Question.getQuestionById(id) }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        val rootView = binding.root
        tvQuestion = binding.tvQuestion
        rgQuestion = binding.rgQuestions
        btnNext = binding.btnNext
        btnPrev = binding.btnPrev
        ivQuestion = binding.ivQuestion
        binding.btnNext.setOnClickListener(){
        }
        updateContent()

        return rootView
    }
    private fun updateContent() {   //Function to load data to the app layout

        // Show the placeholder content as text in a TextView.
        question?.let {
            tvQuestion.text = it.question
            ivQuestion.setImageResource(question!!.image.getImage(context))
            rb1.text = it.answers[0]
            rgQuestion.addView(rb1)
            rb2.text = it.answers[1]
            rgQuestion.addView(rb2)
            rb3.text = it.answers[2]
            rgQuestion.addView(rb3)
            rb4.text = it.answers[3]
            rgQuestion.addView(rb4)
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