package com.viizfo.coroutinesexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService{

    //Given a username and pass, it retrieves a user
    suspend fun doLogin(user:String, pass:String):User = withContext(Dispatchers.IO){
        //Request user, accessing a DataBase or remote API
        User("Carlos")
    }

    //Given a user, it retrieves a list of Users which are current friends
    suspend fun requestCurrentFriends(user:User): List<User> = withContext(Dispatchers.IO){
        //Request current friends, accessing a DataBase or remote API
        listOf(User("John"), User("Anna"))
    }

    //Given a user, it retrieves a list of Users which are suggested friends
    suspend fun requestSuggestedFriends(user:User): List<User> = withContext(Dispatchers.IO){
        //Request suggested friends, accessing a DataBase or remote API
        listOf(User("Jane"), User("Mike"))
    }
}