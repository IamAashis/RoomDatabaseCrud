package com.example.todoapproom.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String? = null,
)
