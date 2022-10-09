package com.viizfo.p1_converter

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.viizfo.p1_converter.databinding.ActivityMainBinding
import java.lang.Integer.toBinaryString
import kotlin.math.pow

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private var resultado = ""  //Global variable for the result of the operation
    private lateinit var binding: ActivityMainBinding   //Creating the binding variable
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.switchBinDec.setOnClickListener {           //Listener on the switch to control the editTexts available for the user to write
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
        binding.buttonCalculate.setOnClickListener {    //Listener on the button to convert the numbers
            if(binding.switchBinDec.isChecked){         //if switch = ON converts decimal to binary
                resultado = binding.editTextDecimal.text.toString()     //it gets the number
                if(resultado.isEmpty()){                                //if there is no number, it throws a Toast
                    val text = "Debes ingresar un número"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else if(resultado.length > 4 || resultado.toInt() > 1023 ){   //if theres i a number but its > 4 digits OR > 1023 it throws an error in the same field of the result
                    binding.editTextBinary.setText("Error: Number too large")
                } else {                                                        //if its all ok, it shows the result in the binary editText
                    val converted = convertDecimalToBinary(resultado.toInt())
                    binding.editTextBinary.setText(converted)
                }
            } else {    //if switch is IFF
                resultado = binding.editTextBinary.text.toString()      //it gets the number
                if(resultado.isEmpty()){                                //if there is no number, it throws a Toast
                    val text = "Debes ingresar un número"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else if(resultado.length > 10){                       //if the number has more than 10 digits, it throws a toast
                    val text = "Máximo 10 dígitos"
                    val duration = Toast.LENGTH_LONG
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else if(!isBinary(resultado.toInt())){                //if isn't a binary number, it throws a toast
                    val text = "Debes ingresar un número binario"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                } else {                                                //if its all ok, it shows the result in the binary editText
                    val converted = convertBinaryToDecimal(resultado.toLong())
                    binding.editTextDecimal.setText(converted)
                }
            }
        }
    }
    private fun convertBinaryToDecimal(num: Long): String {     //function to convert binary to decimal
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
    private fun convertDecimalToBinary(num: Int): String {  //function to convert decimal to binary
        val num = num
        return toBinaryString(num)
    }
    private fun isBinary(num: Int): Boolean{    //function to check if a number is a binary number
        var num = num
        while (num != 0) {
            if (num % 10 > 1) {return false}
            num /= 10
        }
        return true
    }
}
