package com.viizfo.p4_livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.viizfo.p4_livedata.databinding.FragmentLanguageBinding
import com.viizfo.p4_livedata.viewmodel.LanguageViewModel

class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLanguageBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]
        //we collect the data of the appropriate image from the viewModel using the liveData
        languageViewModel.languageLiveData.observe(viewLifecycleOwner) { imageID ->
            binding.ivLanguage.setImageResource(imageID)    //we set the imageview image with the data from the viewModel
        }
        //We do the same with the langName variable to place the name of the language in the textView, again using LiveData and the viewModel
        languageViewModel.langNameLiveData.observe(viewLifecycleOwner) { langName ->    //we set the textview text with the viewmodel data
            if(langName.equals("LANGUAGE1")){
                binding.tvLanguage.text = "C++"
            }else if(langName.equals("LANGUAGE2")){
                binding.tvLanguage.text = "C#"
            }else if(langName.equals("LANGUAGE3")){
                binding.tvLanguage.text = "JAVA"
            }else if(langName.equals("LANGUAGE4")){
                binding.tvLanguage.text = "JAVASCRIPT"
            }else if(langName.equals("LANGUAGE5")){
                binding.tvLanguage.text = "KOTLIN"
            }else if(langName.equals("LANGUAGE6")){
                binding.tvLanguage.text = "PYTHON"
            }else if(langName.equals("LANGUAGE7")){
                binding.tvLanguage.text = "TYPESCRIPT"
            }
        }
    }
}