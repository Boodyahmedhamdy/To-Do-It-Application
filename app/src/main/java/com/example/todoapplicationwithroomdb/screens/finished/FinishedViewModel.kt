package com.example.todoapplicationwithroomdb.screens.finished

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.database.TodoDao
import com.example.todoapplicationwithroomdb.screens.TodoAdapter
import kotlinx.coroutines.*

class FinishedViewModel(
    val dataSource: TodoDao,
    application: Application
): AndroidViewModel(application) {

    // ---------------------------- COROUTINES ------------------
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    // ------------------------- LOGIC ---------------------------------
    private val _adapter: MutableLiveData<TodoAdapter> = MutableLiveData()
    val adapter: LiveData<TodoAdapter>
        get() = _adapter


    private val _allFinished: MutableLiveData<List<Todo>> = MutableLiveData()
    val allFinished: LiveData<List<Todo>>
        get() = _allFinished


    init {
        initializeUI()
    }
    private fun initializeUI() {
        uiScope.launch {
            _allFinished.value = getAllFinished()
            _adapter.value = TodoAdapter(_allFinished.value ?: emptyList())
        }
    }
    private suspend fun getAllFinished(): List<Todo> {
        return withContext(Dispatchers.IO) {
            dataSource.getFinishedTodos()
        }
    }

}












