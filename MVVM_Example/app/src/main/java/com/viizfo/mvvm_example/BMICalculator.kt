package com.viizfo.mvvm_example

import kotlin.math.pow
import java.lang.Exception

typealias OnWrongHeight = (error:String)->Unit
typealias OnWrongWeight = (error:String)->Unit
typealias onError = (error:String)->Unit
typealias OnSuccess = (bmi:Double)->Unit
typealias OnLoading = (loading:Boolean)->Unit


class BMICalculator{

    data class Request(
        val weight:Double,
        val height:Double
    )

    private fun calcMBI(weight: Double, height: Double):Double = weight/ (height/100).pow(2)

    /***************WITH FUNCTIONS***********************************/
    //Long calculating function
    fun calculateWithFunctions(request:Request,onSuccess: OnSuccess, onError: onError, onLoading: OnLoading, onWrongWeight: OnWrongWeight?, onWrongHeight: OnWrongHeight?){

        onLoading(true)
        val minHeight= 50
        val minWeight = 10

        var error = false

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        //If height is lower than minHeight call ourCallback
        if(minHeight > request.height){
            onWrongHeight?.let {
                it.invoke("Height should be bigger")
                error = true
            }
        }
        if(minWeight > request.weight){
            onWrongHeight?.let {
                it.invoke("Weight should be bigger")
                error = true
            }
        }

        if(!error){
            //All works fine
            try{
                val bmi = calcMBI(request.weight, request.height)
                onSuccess(bmi)
            }catch (e:Exception){
                onError(e.toString())
            }
        }
        onLoading(false)



    }


    /***************WITH SEALED CLASS***********************************/

    sealed class Response{
        class OKResult(val result:Double):Response()
        class WrongWeight(val error:String):Response()
        class WrongHeight(val error:String):Response()
    }


    //Long calculating function with Sealed class
    fun calculateWithSealed(request:Request,onLoading: OnLoading?):Response{
        onLoading?.invoke(true)
        val minHeight= 50
        val minWeight = 10

        if(minWeight > request.weight) return ( Response.WrongWeight("Weight should be bigger"))
        if(minHeight > request.height) return ( Response.WrongHeight("Height should be bigger"))




        Thread.sleep(5000)
        println("${Thread.currentThread().name}")

        //All works fine
        onLoading?.invoke(false)
        return Response.OKResult(calcMBI(request.weight, request.height))

    }




    /***************WITH CALLBACK INTERFACE***********************************/

    interface BMIResponse{
        fun onSuccess(result:Double)
        fun onHeightError(error:String)
        fun onWeightError(error:String)
        fun onError(error:String)
        fun onLoading(loading: Boolean)
    }

    fun calculateWithCallback(request: Request, bmiResponse: BMIResponse){
        bmiResponse.onLoading(true)
        val minHeight= 50
        val minWeight = 10

        var error = false

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        //If height is lower than minHeight call ourCallback
        if(minHeight > request.height){
            bmiResponse.onHeightError("Height should be bigger")
            error = true
        }
        if(minWeight > request.weight){
            bmiResponse.onWeightError("Weight should be bigger")
            error = true
        }

        if(!error){
            //All works fine
            try{
                val bmi = calcMBI(request.weight, request.height)
                bmiResponse.onSuccess(bmi)
            }catch (e:Exception){
                bmiResponse.onError(e.toString())
            }
        }
        bmiResponse.onLoading(false)
    }

}