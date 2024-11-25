package com.example.tugastodolist

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private var todoList: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButtonDone: RadioButton = itemView.findViewById(R.id.radioButtonDone)
        val textViewTodo: TextView = itemView.findViewById(R.id.textViewTodo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.textViewTodo.text = todo.text
        // Remove the setOnCheckedChangeListener here
    }

    override fun getItemCount(): Int = todoList.size

    fun updateList(newList: MutableList<Todo>) {
        todoList = newList
        notifyDataSetChanged()
    }

    init { // Set the listener only once in the adapter's initializer
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return todoList[position].hashCode().toLong()
    }

    override fun onViewAttachedToWindow(holder: TodoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.radioButtonDone.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val position = holder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val todo = todoList[position]
                    AlertDialog.Builder(holder.itemView.context)
                        .setTitle("Mark as Done?")
                        .setMessage("Are you sure you want to mark '${todo.text}' as done?")
                        .setPositiveButton("Yes") { _, _ ->
                            todoList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                        }
                        .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.radioButtonDone.setOnCheckedChangeListener(null)
    }
}
