package com.viizfo.coroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userService = UserService()

        runBlocking(Dispatchers.Main) { //We can remove the dispatcher runBlocking{ ... }

            val user = userService.doLogin("Carlos", "1234")

            val currentFriends = userService.requestCurrentFriends(user)
            val suggestedFriends = userService.requestSuggestedFriends(user)

            val userWithFriends = user.copy(friends = currentFriends + suggestedFriends)
        }
    }
}