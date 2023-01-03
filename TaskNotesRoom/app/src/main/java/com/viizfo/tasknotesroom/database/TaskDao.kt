package com.viizfo.tasknotesroom.database

import androidx.room.*
import com.viizfo.tasknotesroom.database.entities.TaskEntity

@Dao
interface TaskDao:MyDao {
    @Query("SELECT * FROM task_entity")
    override fun getAllTasks(): MutableList<TaskEntity>

    @Insert
    override fun addTask(taskEntity : TaskEntity):Long

    @Query("SELECT * FROM task_entity WHERE id LIKE :id")
    override fun getTaskById(id: Long): TaskEntity

    @Update
    override fun updateTask(taskEntity: TaskEntity):Int

    @Delete
    override fun deleteTask(taskEntity: TaskEntity):Int
}