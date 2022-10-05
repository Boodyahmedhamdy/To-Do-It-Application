package com.example.todoapplicationwithroomdb.screens.todopreview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.database.TodoDao
import com.example.todoapplicationwithroomdb.screens.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class TodoPreviewDialogViewModelFactory(
    private val dataSource: TodoDao,
    private val application: Application,
    private val todo: Todo
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoPreviewDialogViewModel::class.java)) {
            return TodoPreviewDialogViewModel(dataSource, application, todo) as T
        }
        throw IllegalArgumentException("invalid ViewModel class")
    }
}