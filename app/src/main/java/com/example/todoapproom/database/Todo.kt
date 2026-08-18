package com.example.todoapproom.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String? = null,
    var createdAt: Date? = Date() // stored as Long, see Converters
)
