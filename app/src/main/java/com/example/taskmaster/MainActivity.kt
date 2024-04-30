package com.example.taskmaster

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(taskList, ::deleteTask)
        recyclerView.adapter = taskAdapter
    }

    fun addTask(view: View) {
        val taskEditText = findViewById<EditText>(R.id.taskEditText)
        val taskName = taskEditText.text.toString().trim()
        if (taskName.isNotEmpty()) {
            val creationTime = System.currentTimeMillis()
            val deletionTime = creationTime + 10000 // 10 seconds duration
            val task = TaskModel(taskName, creationTime, deletionTime)
            taskList.add(task)
            taskAdapter.notifyDataSetChanged()
            taskEditText.text.clear()
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTask(position: Int) {
        if (position >= 0 && position < taskList.size) {
            taskList.removeAt(position)
            taskAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
        } else {
            // Handle the case when the taskList is empty or the position is invalid
            Toast.makeText(this, "No tasks to delete", Toast.LENGTH_SHORT).show()
        }
    }
}
