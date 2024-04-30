package com.example.taskmaster

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val taskName = intent?.getStringExtra("TASK_NAME")
        Toast.makeText(context, "Time for task \"$taskName\"!", Toast.LENGTH_SHORT).show()
    }
}
