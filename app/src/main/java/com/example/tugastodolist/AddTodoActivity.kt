package com.example.tugastodolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val editTextTodo = findViewById<EditText>(R.id.editTextTodo)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val todoText = editTextTodo.text.toString()
            if (todoText.isNotBlank()) {
                val intent = Intent().apply {
                    putExtra("TODO_TEXT", todoText)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
