package com.viizfo.p1_converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.p1_converter.databinding.ActivityMainBinding
import java.lang.Integer.toBinaryString

class MainActivity : AppCompatActivity() {

    var resultado = ""
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        binding.switchBinDec.setOnClickListener(){
            if(binding.switchBinDec.isChecked){
                binding.editTextDecimal.setEnabled(false)
                binding.editTextBinary.setEnabled(true)
            } else {
                binding.editTextDecimal.setEnabled(true)
                binding.editTextBinary.setEnabled(false)
            }
        }

        binding.buttonCalculate.setOnClickListener(){
            if(binding.switchBinDec.isChecked){
                resultado = binding.editTextDecimal.text.toString()
                val converted = convertDecimaltoBinary(resultado.toInt())
                binding.editTextBinary.setText(converted)
            } else {
                resultado = binding.editTextBinary.text.toString()
                val converted = convertBinaryToDecimal(resultado.toLong())
                binding.editTextDecimal.setText(converted)
            }

        }
    }
    fun convertBinaryToDecimal(num: Long): String {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber.toString()
    }
    fun convertDecimaltoBinary(num: Int): String {
        var num = num
        val binary = toBinaryString(num)
        return binary
    }
}