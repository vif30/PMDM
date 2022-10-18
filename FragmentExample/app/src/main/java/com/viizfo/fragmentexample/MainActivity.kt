package com.viizfo.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {
    private var _binding: MainActivity? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickFragmentButton(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
    private fun loadFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.)
    }
}