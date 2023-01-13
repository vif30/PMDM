package com.viizfo.volleyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

const val RESPONSE_TAG = "RESPONSE_STRING"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getStringVolley() {
        //Create an instances of Volley's queue
        var queue = Volley.newRequestQueue(this)

        //URL
        val url = "https:/url_that_returns_us/a_string"

        //String Request
        val stringRequest = StringRequest(
            url,
            { response ->
                //Response is the String retrieved from de URL
                Log.i(RESPONSE_TAG, "Response is: ${response}")
            },
            { error -> error.printStackTrace() }
        )

        //Adding our request to the Volley's queue
        queue.add(stringRequest)

    }

    fun getJSONObjectVolley() {
        //Create an instances of Volley's queue
        var queue = Volley.newRequestQueue(this)

        //URL
        val url = "https:/url_that_returns_us/a_json_object"

        //jsonObject Request
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                //Response is the jsonObject retrieved from de URL
                Log.i(RESPONSE_TAG, "Response is: ${response}")
            },
            { error -> error.printStackTrace() }
        )

        //Adding our request to the Volley's queue
        queue.add(jsonObjectRequest)

    }

    fun postJSONObjectVolley() {
        //Create an instances of Volley's queue
        var queue = Volley.newRequestQueue(this)

        //URL
        val url = "https:/url_that_returns_us/a_json_object"

        val jsonObject = JSONObject() //The jsonObject sent
        jsonObject.put("name", "Gandalf")
        jsonObject.put("class", "Wizard")

        //jsonObject Request
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            { response ->
                //Response is the jsonObject retrieved from de URL
                Log.i(RESPONSE_TAG, "Response is: ${response}")
            },
            { error -> error.printStackTrace() }
        )

        //Adding our request to the Volley's queue
        queue.add(jsonObjectRequest)

    }

    fun getJSONArrayVolley() {
        //Create an instances of Volley's queue
        var queue = Volley.newRequestQueue(this)

        //URL
        val url = "https:/url_that_returns_us/a_json_object"

        //jsonArrayRequest Request
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                //Response is the jsonArray retrieved from de URL
                Log.i(RESPONSE_TAG, "Response is: ${response}")
            },
            { error -> error.printStackTrace() }
        )

        //Adding our request to the Volley's queue
        queue.add(jsonArrayRequest)

    }


    fun postJSONArrayVolley() {
        //Create an instances of Volley's queue
        var queue = Volley.newRequestQueue(this)

        //URL
        val url = "https:/url_that_returns_us/a_json_object"

        val jsonArray = JSONArray()
        jsonArray.put(JSONObject().put("name", "Gandalf").put("class", "Wizard"))
        jsonArray.put(JSONObject().put("name", "Frodo").put("class", "Hobbit"))

        //jsonArrayRequest Request
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.POST,
            url,
            jsonArray,
            { response ->
                //Response is the jsonArray retrieved from de URL
                Log.i(RESPONSE_TAG, "Response is: ${response}")
            },
            { error -> error.printStackTrace() }
        )

        //Adding our request to the Volley's queue
        queue.add(jsonArrayRequest)

    }
}