package com.example.todoapproom.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapproom.Constants.DatabaseConstants

@Database(entities = [Todo::class, Users::class], version = 2)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var INSTANCE: TodoDatabase? = null // if their is any changes value it changes all values in threads

        fun getInstance(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DatabaseConstants.TABLENAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}