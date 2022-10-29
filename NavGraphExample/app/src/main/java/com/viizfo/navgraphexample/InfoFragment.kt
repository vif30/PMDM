package com.viizfo.navgraphexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.viizfo.navgraphexample.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private val args: InfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentInfoBinding

    private lateinit var login:Login

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity = context as AppCompatActivity

        val actionBar: ActionBar? = activity.supportActionBar
        actionBar?.hide()

        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        navController = findNavController()
        //Get the Login passed by LoginFragment
        login = args.loginInfo //loginInfo is our login_info safe arg

        binding.btnInfoNext.setOnClickListener {


            //Getting Fragment UI info and setting variables.
            login.email = binding.etEmail.text.toString()
            login.birthDate = binding.etBirthDate.text.toString()

            //Creating the action object
            val action = InfoFragmentDirections
                .actionInfoFragmentToHomeFragment(login)
            //Navigating
            navController?.navigate(action)


            //navController?.navigate(R.id.action_infoFragment_to_homeFragment, bundle)
        }
    }
}