package com.example.todoapplicationwithroomdb.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "todos")
@Parcelize
data class Todo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    val id: Long = 0L,

    @ColumnInfo(name = "todo_title")
    var title: String = "title",

    @ColumnInfo(name = "todo_time_created_millis")
    val timeCreatedMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "todo_is_favorate")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "todo_is_finished")
    var isFinished: Boolean = false
): Parcelable