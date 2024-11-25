package com.example.tugastodolist

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(private var todoList: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButtonDone: RadioButton = itemView.findViewById(R.id.radioButtonDone)
        val textViewTodo: TextView = itemView.findViewById(R.id.textViewTodo)
        val textViewDeadline: TextView = itemView.findViewById(R.id.textViewDeadline)
        val buttonEdit: ImageButton = itemView.findViewById(R.id.buttonEdit) // Added this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.textViewTodo.text = todo.text
        holder.radioButtonDone.isChecked = todo.isDone
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        holder.textViewDeadline.text = todo.deadline?.let { dateFormat.format(it) } ?: ""

        holder.buttonEdit.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddTodoActivity::class.java).apply {
                putExtra("TODO_TEXT", todo.text)
                putExtra("TODO_ID", position) // Pass the position to identify the todo
                todo.deadline?.let { putExtra("TODO_DEADLINE", it.time) }
            }
            (holder.itemView.context as? MainActivity)?.editTodoLauncher?.launch(intent)
        }
    }

    override fun getItemCount(): Int = todoList.size

    fun updateList(newList: MutableList<Todo>) {
        todoList = newList
        notifyDataSetChanged()
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return todoList[position].hashCode().toLong()
    }

    override fun onViewAttachedToWindow(holder: TodoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.radioButtonDone.setOnCheckedChangeListener { _, isChecked ->
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val todo = todoList[position]
                todo.isDone = isChecked
                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Mark as Done?")
                    .setMessage("Are you sure you want to mark '${todo.text}' as done?")
                    .setPositiveButton("Yes") { _, _ ->
                        todoList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, itemCount)
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        todo.isDone = !isChecked
                        notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.radioButtonDone.setOnCheckedChangeListener(null)
    }
}
