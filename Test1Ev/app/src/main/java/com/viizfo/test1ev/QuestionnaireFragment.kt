package com.viizfo.test1ev

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.navArgs
import com.viizfo.test1ev.databinding.FragmentQuestionnaireBinding
import com.viizfo.test1ev.utils.OnBtnQuestionnaireClick
import com.viizfo.test1ev.utils.setFromString

class QuestionnaireFragment : Fragment() {
    private lateinit var binding: FragmentQuestionnaireBinding
    private val args:QuestionnaireFragmentArgs by navArgs()
    private var listener:OnBtnQuestionnaireClick? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnBtnQuestionnaireClick){
            listener = context
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val info = args.questionInfo
        if(info.isFirst){
            binding.btnPrev.visibility = View.INVISIBLE
        }else{
            binding.btnPrev.visibility = View.VISIBLE
        }
        if(info.isLast){
            binding.btnNext.text = "SEND"
        }else{
            binding.btnNext.text = "NEXT"
        }
        binding.tvQuestion.text = info.question
        binding.ivQuestion.setFromString(info.image)
        info.answers.forEachIndexed { i, text ->
            val rb = RadioButton(requireContext())
            rb.text = text
            rb.id = i
            rb.textSize = 22f
            if(info.savedAnswer == i) rb.isChecked = true
            rb.setTextColor(requireContext().getColor(R.color.purple_500))
            binding.rgQuestions.addView(rb)
        }
        binding.btnNext.setOnClickListener{
            val numAnswer = binding.rgQuestions.checkedRadioButtonId
            listener?.onNextClick(numAnswer)
        }
        binding.btnPrev.setOnClickListener {
            val numAnswer = binding.rgQuestions.checkedRadioButtonId
            listener?.onPrevClick(numAnswer)
        }
        requireActivity().title = getString(R.string.questionnaire_title, info.numQuestions, info.totalQuestions)
    }

}