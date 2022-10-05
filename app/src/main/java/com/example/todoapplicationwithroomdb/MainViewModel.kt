package com.example.todoapplicationwithroomdb

import android.app.Application
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.*
import com.example.todoapplicationwithroomdb.database.TodoDao
import kotlinx.coroutines.*

class MainViewModel(
    val dataSource: TodoDao,
    application: Application
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "MainViewModel"
        fun createLog(message: String) {
            Log.i(TAG, message)
        }
    }

    // -------------- COROUTINES ---------------
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    private val _onHomeTab = MutableLiveData<Boolean>(true)
    val onHomeTab: LiveData<Boolean>
        get() = _onHomeTab

    private val _onFavorateTab = MutableLiveData<Boolean>(false)
    val onFavorateTab: LiveData<Boolean>
        get() = _onFavorateTab

    private val _onFinishedTab = MutableLiveData<Boolean>(false)
    val onFinishedTab: LiveData<Boolean>
        get() = _onFinishedTab

    private val _onClearDatabase = MutableLiveData<Boolean>(false)
    val onClearDatabase: LiveData<Boolean>
        get() = _onClearDatabase


    fun onClickOnFavorateTab() {
        _onFavorateTab.value = true
        _onHomeTab.value = false
        _onFinishedTab.value = false
    }

    fun onClickOnHomeTab() {
        _onHomeTab.value = true
        _onFavorateTab.value = false
        _onFinishedTab.value = false
    }

    fun onClickOnFinishedTab() {
        _onFinishedTab.value = true
        _onFavorateTab.value = false
        _onHomeTab.value = false
    }

    fun onClearDatabase() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.clearDatabase()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}