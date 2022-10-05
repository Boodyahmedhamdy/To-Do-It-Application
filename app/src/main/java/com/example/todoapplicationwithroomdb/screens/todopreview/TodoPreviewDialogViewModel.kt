package com.example.todoapplicationwithroomdb.screens.todopreview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.database.TodoDao
import kotlinx.coroutines.*

class TodoPreviewDialogViewModel(
    val dataSource: TodoDao,
    application: Application,
    val todo: Todo
): AndroidViewModel(application) {
    // ----------------- DATABASE *COROUTINES* ---------------------

    private val job: Job = Job()
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)







    // ----------------- NAVIGATION ---------------------
    // navigation related
    private val _onClickCreate: MutableLiveData<Boolean> = MutableLiveData(false)
    val onClickCreate: LiveData<Boolean>
        get() = _onClickCreate

    private val _onClickCancel: MutableLiveData<Boolean> = MutableLiveData(false)
    val onClickCancel: LiveData<Boolean>
        get() = _onClickCancel


    // ----------------- NAVIGATION ---------------------
    // ----------------- NAVIGATION ---------------------
    // ----------------- NAVIGATION ---------------------

    init {
        Log.d("boody", "TodoPreviewViewModel is created")
    }



    fun onCreate() {
        uiScope.launch {
            insertTodo(todo)
        }
        _onClickCreate.value = true
    }

    private suspend fun insertTodo(todo: Todo) {
        withContext(Dispatchers.IO) {
            dataSource.insertTodo(todo)
        }
    }

    fun onCancel() {
        _onClickCancel.value = true
    }


    // --------------------------- WHEN VIEW MODEL IS DESTROYED -----------------------------
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }



}