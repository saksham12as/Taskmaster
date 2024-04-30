package com.example.taskmaster

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<TaskModel>, private val deleteListener: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
        val timerTextView: TextView = itemView.findViewById(R.id.timerTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        var countDownTimer: CountDownTimer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = tasks[position]
        holder.taskTextView.text = currentItem.taskName

        val deletionTime = currentItem.deletionTime
        val currentTime = System.currentTimeMillis()

        // Ensure deletion time is in the future to avoid negative timer duration
        if (deletionTime > currentTime) {
            val timerDuration = deletionTime - currentTime
            holder.countDownTimer?.cancel() // Cancel any existing timer
            holder.countDownTimer = object : CountDownTimer(timerDuration, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    holder.timerTextView.text = formatTime(millisUntilFinished)
                }

                override fun onFinish() {
                    // Remove the task when the timer finishes
                    deleteListener(holder.adapterPosition)
                }
            }.start()
        } else {
            // Deletion time has already passed, remove the task immediately
            deleteListener(holder.adapterPosition)
        }

        holder.deleteButton.setOnClickListener {
            // Cancel the countdown timer and remove the task when the delete button is clicked
            holder.countDownTimer?.cancel()
            deleteListener(holder.adapterPosition)
        }
    }

    override fun getItemCount() = tasks.size

    // Utility function to format time in HH:MM:SS format
    private fun formatTime(milliseconds: Long): String {
        // Convert milliseconds to seconds
        val seconds = milliseconds / 1000

        // Calculate hours, minutes, and remaining seconds
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
        class TaskAdapter(private val tasks: MutableList<TaskModel>, private val deleteListener: (Int) -> Unit) :
            RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

            inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
                val timerTextView: TextView = itemView.findViewById(R.id.timerTextView)
                val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
                var countDownTimer: CountDownTimer? = null
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
                return TaskViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
                val currentItem = tasks[position]
                holder.taskTextView.text = currentItem.taskName

                val deletionTime = currentItem.deletionTime
                val currentTime = System.currentTimeMillis()

                // Ensure deletion time is in the future to avoid negative timer duration
                if (deletionTime > currentTime) {
                    val timerDuration = deletionTime - currentTime
                    holder.countDownTimer?.cancel() // Cancel any existing timer
                    holder.countDownTimer = object : CountDownTimer(timerDuration, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            holder.timerTextView.text = formatTime(millisUntilFinished)
                        }

                        override fun onFinish() {
                            // Remove the task when the timer finishes
                            deleteListener(holder.adapterPosition)
                        }
                    }.start()
                } else {
                    // Deletion time has already passed, remove the task immediately
                    deleteListener(holder.adapterPosition)
                }

                holder.deleteButton.setOnClickListener {
                    // Cancel the countdown timer and remove the task when the delete button is clicked
                    holder.countDownTimer?.cancel()
                    deleteListener(holder.adapterPosition)
                }
            }

            override fun getItemCount() = tasks.size

            // Utility function to format time in HH:MM:SS format
            private fun formatTime(milliseconds: Long): String {
                // Convert milliseconds to seconds
                val seconds = milliseconds / 1000

                // Calculate hours, minutes, and remaining seconds
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val remainingSeconds = seconds % 60

                return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
            }
        }
    }
}