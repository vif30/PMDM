package com.viizfo.mvvm_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viizfo.mvvm_example.BMICalculator
import com.viizfo.mvvm_example.BMICalculator.BMIResponse
import kotlinx.coroutines.*

class BMICalculatorViewModel: ViewModel() {

    private val bmiCalculator: BMICalculator = BMICalculator()


    val bmi : MutableLiveData<Double> = MutableLiveData()
    val heightError : MutableLiveData<String> = MutableLiveData()
    val weightError : MutableLiveData<String> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()



    //Choose which of the three methods you want to use
    fun calculateBMI(weight:Double,height:Double){

        //calculateBMIFunctions(weight,height)
        calculateBMICallBack(weight,height)
        //calculateBMISealed(weight,height)

    }

    /**********WITH FUNCTIONS *************************/
    private fun calculateBMIFunctions(weight: Double, height: Double) {
        //Using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            //Using callbacks
            bmiCalculator.calculateWithFunctions(BMICalculator.Request(weight, height),
                onSuccess = { mBMI->
                    bmi.postValue(mBMI)
                    heightError.postValue("")
                    weightError.postValue("")
                    error.postValue("")
                },
                onError = {
                        e -> error.postValue(e)
                },
                onLoading = {
                        isLoading -> loading.postValue(isLoading)
                },
                onWrongWeight = {
                        e -> weightError.postValue(e)
                }, null)

        }
    }

    /******************WITH CALLBACK*************************************/
    private fun calculateBMICallBack(weight: Double, height: Double) {


        CoroutineScope(Dispatchers.IO).launch {
            bmiCalculator.calculateWithCallback(
                BMICalculator.Request(weight,height),
                object: BMICalculator.BMIResponse {
                    override fun onSuccess(result: Double) {
                        bmi.postValue(result)
                        error.postValue("")
                        heightError.postValue("")
                        weightError.postValue("")
                    }

                    override fun onHeightError(error: String) {
                        heightError.postValue(error)
                    }

                    override fun onWeightError(error: String) {
                        weightError.postValue(error)
                    }

                    override fun onError(mError: String) {
                        error.postValue(mError)
                    }

                    override fun onLoading(mLoading: Boolean) {
                        loading.postValue(mLoading)
                    }

                })


        }
    }


    /******************WITH SEALED***************************************/

    private fun calculateBMISealed(weight: Double, height: Double) {

        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            bmiCalculator.calculateWithSealed(BMICalculator.Request(weight, height),null).also{ res ->
                when(res){
                    is BMICalculator.Response.OKResult -> {
                        bmi.postValue(res.result)
                        heightError.postValue("")
                        weightError.postValue("")
                        error.postValue("") }
                    is BMICalculator.Response.WrongHeight ->{
                        heightError.postValue(res.error)
                    }
                    is BMICalculator.Response.WrongWeight ->{
                        weightError.postValue(res.error)
                    }
                }

            }
            loading.postValue(false)
        }
    }




}