package com.example.tugastodolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private val todoList = mutableListOf<Todo>()

    private val addTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val todoText = result.data?.getStringExtra("TODO_TEXT") ?: ""
            if (todoText.isNotBlank()) {
                val newTodo = Todo(todoText)
                todoList.add(newTodo)
                todoAdapter.notifyItemInserted(todoList.size -1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvTODO)
        recyclerView.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(todoList)
        recyclerView.adapter = todoAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.addItemButton).setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            addTodoLauncher.launch(intent)
        }
    }
}
