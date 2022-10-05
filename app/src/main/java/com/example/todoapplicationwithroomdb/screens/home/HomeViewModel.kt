package com.example.todoapplicationwithroomdb.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.database.TodoDao
import com.example.todoapplicationwithroomdb.screens.TodoAdapter
import kotlinx.coroutines.*

class HomeViewModel(
    val dataSource: TodoDao,
    application: Application
): AndroidViewModel(application) {

    // -------------------------------- COROUTINES -------------------------------
    // job to cancel all coroutines
    private val job: Job = Job()

    // uiScope to run UI stuff
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

    // -------------------------------- NAVIGATION -------------------------------
    private val _onClickCreate: MutableLiveData<Boolean> = MutableLiveData(false)
    val onClickCreate: LiveData<Boolean>
        get() = _onClickCreate

    // ------------------------------ LOGIC ------------------------------------

    // the list which will be on the adapter
    private val _allTodos: MutableLiveData<List<Todo>> = MutableLiveData()
    val allTodos: LiveData<List<Todo>>
        get() = _allTodos

    // the _todo_ object will be created when create button is clicked
    private val _currentTodo: MutableLiveData<Todo?> = MutableLiveData()
    val currentTodo: LiveData<Todo?>
        get() = _currentTodo

    private val _adapter: MutableLiveData<TodoAdapter> = MutableLiveData()
    val adapter: LiveData<TodoAdapter>
        get() = _adapter

    private val testTodoList: List<Todo> = listOf(
        Todo(title = "boody"),
        Todo(title = "ahmed"),
        Todo(title = "hamdy")
    )

    // ---------------------------------------------------------------



    // first thing will happen when screen is loaded
    init {
        initializeUI()
    }

    private fun initializeUI() {
        uiScope.launch {
            _allTodos.value = getAllTodosFromDatabase()
            _adapter.value = TodoAdapter(_allTodos.value ?: testTodoList)
            Log.d("boody", "homeViewModel is created")
        }
    }

    private suspend fun getAllTodosFromDatabase(): List<Todo> {
        return withContext(Dispatchers.IO) {
            dataSource.getAllTodos()
        }
    }

    fun onCreateTodo() {
        _onClickCreate.value = true
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}