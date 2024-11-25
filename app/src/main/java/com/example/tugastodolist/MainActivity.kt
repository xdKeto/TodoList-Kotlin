package com.example.tugastodolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // ... other code ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ... other code ...

        // ... other code ...
    }
}

class TodoAdapter(private var todoList: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    // ... other code ...

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.textViewTodo.text = todo.text
        holder.radioButtonDone.isChecked = todo.isDone
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        holder.textViewDeadline.text = todo.deadline?.let { dateFormat.format(it) } ?: ""
    }

    // ... other code ...

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButtonDone: RadioButton = itemView.findViewById(R.id.radioButtonDone)
        val textViewTodo: TextView = itemView.findViewById(R.id.textViewTodo)
        val textViewDeadline: TextView = itemView.findViewById(R.id.textViewDeadline)
    }
}
