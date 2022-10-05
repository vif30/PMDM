package com.viizfo.p1_converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.viizfo.p1_converter.databinding.ActivityMainBinding
import java.lang.Integer.toBinaryString
import kotlin.math.pow

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
                binding.editTextDecimal.setText("")
                binding.editTextBinary.setText("")
                binding.editTextDecimal.isEnabled = true
                binding.editTextBinary.isEnabled = false
            } else {
                binding.editTextDecimal.setText("")
                binding.editTextBinary.setText("")
                binding.editTextDecimal.isEnabled = false
                binding.editTextBinary.isEnabled = true
            }
        }

        binding.buttonCalculate.setOnClickListener(){
            if(binding.switchBinDec.isChecked){
                resultado = binding.editTextDecimal.text.toString()
                if(resultado.isEmpty()){
                    val text = "Debes ingresar un número"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else if(resultado.toInt() > 1023){
                    binding.editTextBinary.setText("Error: Número demasiado grande")
                } else {
                    val converted = convertDecimalToBinary(resultado.toInt())
                    binding.editTextBinary.setText(converted)
                }
            } else {
                resultado = binding.editTextBinary.text.toString()
                if(resultado.isEmpty()){
                    val text = "Debes ingresar un número"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else if(resultado.length > 10){
                    val text = "Máximo 10 dígitos"
                    val duration = Toast.LENGTH_LONG
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else {
                    val converted = convertBinaryToDecimal(resultado.toLong())
                    binding.editTextDecimal.setText(converted)
                }
            }

        }
    }
    private fun convertBinaryToDecimal(num: Long): String {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
            ++i
        }
        return decimalNumber.toString()
    }
    private fun convertDecimalToBinary(num: Int): String {
        var num = num
        return toBinaryString(num)
    }
}