package com.viizfo.navgraphexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.viizfo.navgraphexample.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val activity = context as AppCompatActivity

        val actionBar: ActionBar? = activity.supportActionBar
        actionBar?.show()
        activity.title = "My Nav Graph App"


        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvInfo.text = "Welcome:\n\nUser: ${args.loginInfo.usr}\nPass: ${args.loginInfo.pass}\nEmail: ${args.loginInfo.email}\nBirth date: ${args.loginInfo.birthDate}\n"
    }
}