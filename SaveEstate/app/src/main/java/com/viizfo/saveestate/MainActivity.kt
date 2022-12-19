package com.viizfo.saveestate
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

const val TAG ="SAVE_STATE_TAG"

const val COUNTER_KEY="COUNTER_KEY"

class MainActivity : AppCompatActivity() {
    private var counter=0

    private val tvCounter: TextView by lazy { findViewById(R.id.tvCounter) }
    private val btnInc: Button by lazy { findViewById(R.id.btnInc) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log("onCreate Method")

        btnInc.setOnClickListener {
            increaseCounter()
        }


    }

    private fun increaseCounter() {
        counter++

        updateTvCounter()
    }

    private fun updateTvCounter() {
        tvCounter.text=counter.toString()
    }

    private fun log(text:String){
        Log.d(TAG, text )
    }

    override fun onResume() {
        super.onResume()
        log("We are onResume")
        updateTvCounter()
    }

    override fun onSaveInstanceState(outState:Bundle){
        super.onSaveInstanceState(outState)
        log("We are onSaveInstanceState")
        outState.putInt(COUNTER_KEY,counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        log("We are onRestoreInstanceState")

        counter=savedInstanceState.getInt(COUNTER_KEY)
    }
}