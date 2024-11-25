package com.example.tugastodolist

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
                //  Add the todoText to your main activity's list (implementation below)
                // For now, just finish the activity
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}
