package com.viizfo.tasknotesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.viizfo.tasknotesroom.database.MyDao
import com.viizfo.tasknotesroom.database.TasksDatabase
import com.viizfo.tasknotesroom.database.entities.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    var myDao:MyDao = TasksDatabase.getInstance(context)

    val taskListLD:MutableLiveData<MutableList<TaskEntity>> = MutableLiveData()
    val updateTaskLD:MutableLiveData<TaskEntity?> = MutableLiveData()
    val deleteTaskLD:MutableLiveData<Int> = MutableLiveData()
    val insertTaskLD:MutableLiveData<TaskEntity> = MutableLiveData()

    fun getAllTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            taskListLD.postValue(myDao.getAllTasks())
        }
    }
    fun add(task:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = myDao.addTask(TaskEntity(name = task))
            val recoveryTask = myDao.getTaskById(id)
            insertTaskLD.postValue(recoveryTask)
        }
    }
    fun delete(task:TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {

            val res = myDao.deleteTask(task)
            if(res>0)
                deleteTaskLD.postValue(task.id)
            else{
                deleteTaskLD.postValue(-1)
            }
        }
    }
    fun update(task:TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            task.isDone = !task.isDone
            val res = myDao.updateTask(task)
            if(res>0)
                updateTaskLD.postValue(task)
            else
                updateTaskLD.postValue(null)
        }
    }
}