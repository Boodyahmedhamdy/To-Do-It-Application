package com.example.todoapplicationwithroomdb.screens.favorate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.database.TodoDao
import com.example.todoapplicationwithroomdb.screens.TodoAdapter
import kotlinx.coroutines.*

class FavorateViewModel(
    val dataSource: TodoDao,
    application: Application
): AndroidViewModel(application) {

    // ------------------- COROUTINES ---------------------
    private val job: Job = Job()
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)


    // ------------------------ LOGIC --------------------------
    private val _allFavorates: MutableLiveData<List<Todo>> = MutableLiveData()
    val allFavorates: LiveData<List<Todo>>
        get() = _allFavorates

    private val _adapter: MutableLiveData<TodoAdapter> = MutableLiveData()
    val adapter: LiveData<TodoAdapter>
        get() = _adapter

    init {
        initializeUI()
    }
    private fun initializeUI() {
        uiScope.launch {
            _allFavorates.value = getAllFavorates()
            _adapter.value = TodoAdapter(_allFavorates.value ?: emptyList())
        }
    }

    private suspend fun getAllFavorates(): List<Todo> {
        return withContext(Dispatchers.IO) {
             dataSource.getFavorateTodos()
        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}









