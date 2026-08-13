package com.example.todoapproom.repository

import com.example.todoapproom.database.Todo
import com.example.todoapproom.database.TodoDao

class TodoRepository(private val dao: TodoDao) {

    val todos = dao.getAllTodos()
    suspend fun add(todo: Todo) = dao.insert(todo)
    suspend fun update(todo: Todo) = dao.update(todo)
    suspend fun delete(todo: Todo) = dao.delete(todo)
}