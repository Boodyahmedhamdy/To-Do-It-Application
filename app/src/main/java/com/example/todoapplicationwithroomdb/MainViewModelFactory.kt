package com.example.todoapplicationwithroomdb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplicationwithroomdb.database.TodoDao

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val dataSource: TodoDao,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("invalid ViewModel class")
    }
}