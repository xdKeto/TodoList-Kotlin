package com.example.tugastodolist

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddTodoActivity : AppCompatActivity() {
    private lateinit var editTextTodo: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewDeadline: TextView
    private var selectedDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        editTextTodo = findViewById(R.id.editTextTodo)
        buttonSave = findViewById(R.id.buttonSave)
        textViewDeadline = findViewById(R.id.textViewDeadline)

        textViewDeadline.setOnClickListener {
            showDatePicker()
        }

        buttonSave.setOnClickListener {
            val todoText = editTextTodo.text.toString()
            if (todoText.isNotBlank()) {
                val intent = Intent().apply {
                    putExtra("TODO_TEXT", todoText)
                    selectedDate?.let { putExtra("TODO_DEADLINE", it.timeInMillis) }
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
            textViewDeadline.text = "${selectedDate?.get(Calendar.DAY_OF_MONTH)}/${selectedDate?.get(Calendar.MONTH) + 1}/${selectedDate?.get(Calendar.YEAR)}"
        }, year, month, day)
        datePickerDialog.show()
    }
}
