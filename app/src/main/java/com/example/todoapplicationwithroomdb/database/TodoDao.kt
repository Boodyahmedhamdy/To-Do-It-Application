package com.example.todoapplicationwithroomdb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    // ------------------------- INSERT ------------------
    @Insert
    fun insertTodo(todo: Todo)


    // ------------------ UPDATE --------------------
    @Update
    fun updateTodo(todo: Todo)


    // -------------------- SELECT ----------------

    @Query("SELECT * FROM todos ORDER BY todo_id DESC")
    fun getAllTodos(): List<Todo>


    @Query("SELECT * FROM todos ORDER BY todo_id DESC LIMIT 1")
    fun getLastTodo(): Todo


    @Query("SELECT * FROM TODOS WHERE todo_is_favorate = 1")
    fun getFavorateTodos(): List<Todo>


    // FIXME: search for boolean
    @Query("SELECT * FROM todos WHERE todo_is_finished = 1")
    fun getFinishedTodos(): List<Todo>


    // ------------ DELETE --------------------

    @Query("DELETE FROM todos WHERE todo_id = :id")
    fun deleteTodoById(id: Long)

    @Query("DELETE FROM todos")
    fun clearDatabase()




}