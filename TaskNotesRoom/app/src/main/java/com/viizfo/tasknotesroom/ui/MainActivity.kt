package com.viizfo.tasknotesroom.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.tasknotesroom.adapters.TasksAdapter
import com.viizfo.tasknotesroom.database.entities.TaskEntity
import com.viizfo.tasknotesroom.databinding.ActivityMainBinding
import com.viizfo.tasknotesroom.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var tasks: MutableList<TaskEntity> = mutableListOf()

    private lateinit var taskViewModel:TaskViewModel

    lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
        }.root)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        taskViewModel.getAllTasks()

        taskViewModel.taskListLD.observe(this){
            tasks.clear()
            tasks.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        }
        taskViewModel.updateTaskLD.observe(this){ taskUpdated ->
            if(taskUpdated == null){
                showMessage("Error updating task")
            }
        }
        taskViewModel.deleteTaskLD.observe(this){ id ->
            if(id != -1){
                val task = tasks.filter {
                    it.id == id
                }[0]
                val pos = tasks.indexOf(task)
                tasks.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }else{
                showMessage("Error deleting task")
            }
        }
        taskViewModel.insertTaskLD.observe(this){
            tasks.add(it)
            recyclerView.adapter?.notifyItemInserted(tasks.size)
        }

        binding.btnAddTask.setOnClickListener {
            addTask()
        }

        setUpRecyclerView()
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
    private fun addTask() {
        taskViewModel.add(binding.etTask.text.toString())
        clearFocus()
        hideKeyboard()
    }
    fun setUpRecyclerView() {
        adapter = TasksAdapter(tasks, { taskEntity ->  updateTask(taskEntity) }, { taskEntity -> deleteTask(taskEntity) })
        recyclerView = binding.rvTask
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun updateTask(taskEntity: TaskEntity) {
        taskViewModel.update(taskEntity)
    }
    private fun deleteTask(taskEntity: TaskEntity) {
        taskViewModel.delete(taskEntity)
    }
    private fun clearFocus(){
        binding.etTask.setText("")
    }
    private fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}