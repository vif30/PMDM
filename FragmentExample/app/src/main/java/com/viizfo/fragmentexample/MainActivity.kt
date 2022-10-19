package com.viizfo.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.viizfo.fragmentexample.databinding.ActivityMainBinding
import com.viizfo.fragmentexample.BlueFragment
import com.viizfo.fragmentexample.RedFragment

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.btnRed.setOnClickListener { replaceFragment(RedFragment()) }
        binding.btnBlue.setOnClickListener { replaceFragment(BlueFragment()) }

    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton(text: String?) {
        TODO("Not yet implemented")
    }
}