package com.example.todoapplicationwithroomdb.screens.finished

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplicationwithroomdb.database.TodoDao

@Suppress("UNCHECKED_CAST")
class FinishedViewModelFactory(
    private val dataSource: TodoDao,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FinishedViewModel::class.java)) {
            return FinishedViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("From FinishedViewModelFactory")
    }
}