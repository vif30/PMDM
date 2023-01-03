package com.viizfo.tasknotesroom.database

import com.viizfo.tasknotesroom.database.entities.TaskEntity

interface MyDao {
    fun getAllTasks(): MutableList<TaskEntity>

    fun addTask(taskEntity : TaskEntity):Long //Id of the new  task

    fun getTaskById(id: Long): TaskEntity

    fun updateTask(taskEntity: TaskEntity):Int //Number of affected rows

    fun deleteTask(taskEntity: TaskEntity):Int //Number of affected rows
}