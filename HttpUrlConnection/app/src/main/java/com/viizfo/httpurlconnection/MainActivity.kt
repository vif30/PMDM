package com.viizfo.httpurlconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.httpurlconnection.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

const val API_URL = "https://webcarlos.com/restperenxisa/users.php"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.btnLogin.setOnClickListener {
            MainScope().launch {
                sendGetRequest(
                    binding.etName.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }

        binding.btnLogup.setOnClickListener {
            MainScope().launch {
                sendPostRequest(
                    binding.etName.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }

    }

    private suspend fun sendGetRequest(userName:String, password:String)= withContext(Dispatchers.IO){

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")

        val mURL = URL("$API_URL?$reqParam")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
                writeResponse(response.toString())
            }
        }
    }

    private suspend fun sendPostRequest(userName:String, password:String)= withContext(Dispatchers.IO){

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
        val mURL = URL("$API_URL")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "POST"

            val wr = OutputStreamWriter(outputStream);
            wr.write(reqParam);
            wr.flush();

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("Response : $response")
                writeResponse(response.toString())
            }
        }
    }


    private suspend fun writeResponse(response:String)= withContext(Dispatchers.Main){
        binding.tvResult.text = response
    }
}