package com.example.todoapplicationwithroomdb.screens.favorate

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplicationwithroomdb.database.TodoDao

@Suppress("UNCHECKED_CAST")
class FavorateViewModelFactory(
    private val dataSource: TodoDao,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavorateViewModel::class.java)) {
            return FavorateViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("can't find class from FavorateViewModelFactory")
    }
}