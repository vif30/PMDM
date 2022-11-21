package com.viizfo.posibleexamen2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.posibleexamen2.databinding.FragmentHomeBinding
import com.viizfo.posibleexamen2.databinding.FragmentQuestionnaireBinding
import com.viizfo.posibleexamen2.model.Question

class homeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.itemList
        val onClickListener = View.OnClickListener { itemView ->
            val Question = itemView.tag as Question
            val bundle = Bundle()
            bundle.putString(
                questionnaireFragment.ARG_ITEM_ID,
                Question.id.toString()
            )
        }
        setupRecyclerView(recyclerView, onClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            Question.getItemQuestion(context?:recyclerView.context),
            onClickListener,
            context?:recyclerView.context
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: MutableList<Question>?,
        private val onClickListener: View.OnClickListener,
        private val context: Context
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = FragmentQuestionnaireBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val question = values!![position]
            holder.ivQuestion.setImageResource(question.image.getImage(context))
            holder.tvQuestion.text = question.question
            //Changing the background color depending on the serie status

            with(holder.itemView) {
                tag = question
                setOnClickListener(onClickListener)
            }
        }
        override fun getItemCount() = values?.size?:0
        inner class ViewHolder(binding: FragmentQuestionnaireBinding) :
            RecyclerView.ViewHolder(binding.root) {
            private var question: Question? = null
            private lateinit var rgQuestion: RadioGroup
            val tvQuestion: TextView = binding.tvQuestion
            val ivQuestion: ImageView = binding.ivQuestion
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}