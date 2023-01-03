package com.viizfo.sharedsettings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.viizfo.sharedsettings.databinding.FragmentMainBinding

class MainFragment:Fragment() {

    private lateinit var binding: FragmentMainBinding

    //listener is listening for every change on the SharedPreferences
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup ?,
        savedInstanceState: Bundle ?,
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate (
            inflater,
            container,
            false
        ) .also {binding=it} .root
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated (view, savedInstanceState)

        /* Obtaining SharedPreferences */
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext()/* Activity context */)

        //Making the callback of the listener
        listener=SharedPreferences.OnSharedPreferenceChangeListener {prefs, key ->
            //Array with all the preferences
            val preferences=prefs.all

            //The key contains the name of the changed preference
            val s=preferences [key] .toString ()
            //Write the changed shared into TextView
            binding.tvInfo.text=s
        }

        //we register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener (listener)

    }

}