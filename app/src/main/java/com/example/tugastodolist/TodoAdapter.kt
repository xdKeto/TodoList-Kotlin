package com.example.tugastodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private var todoList: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTodo: TextView = itemView.findViewById(R.id.textViewTodo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textViewTodo.text = todoList[position].text
    }

    override fun getItemCount(): Int = todoList.size

    fun updateList(newList: MutableList<Todo>){
        todoList = newList
        notifyDataSetChanged()
    }
}
