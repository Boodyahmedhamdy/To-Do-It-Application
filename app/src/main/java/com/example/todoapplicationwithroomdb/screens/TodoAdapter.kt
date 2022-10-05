package com.example.todoapplicationwithroomdb.screens


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplicationwithroomdb.R
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.databinding.ItemTodoBinding

class TodoAdapter(
    val todosList: List<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val binding: ItemTodoBinding = ItemTodoBinding.bind(holder.itemView)
        binding.todoItem = todosList[position]

        /*binding.tvTodoTitle.text = todosList[position].title
        binding.cbFavorate.isChecked = todosList[position].isFavorite
        binding.cbFinished.isChecked = todosList[position].isFinished*/

    }

    override fun getItemCount(): Int {
        return todosList.size
    }

}