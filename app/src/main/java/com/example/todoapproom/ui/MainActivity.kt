package com.example.todoapproom.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import com.example.todoapp.ui.TodoAdapter
import com.example.todoapproom.Constants.DatabaseConstants
import com.example.todoapproom.R
import com.example.todoapproom.database.Todo
import com.example.todoapproom.database.TodoDatabase
import com.example.todoapproom.dialog.AddEditDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: ImageButton

//    private lateinit var database: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        database = Room.databaseBuilder(
//            applicationContext,
//            TodoDatabase::class.java,
//            DatabaseConstants.TABLENAME
//        ).build()

//        GlobalScope.launch {
//            database.todoDao().insert(Todo(title = "Next"))
//        }


        initViews()
        setupAdapter()
        initListener()
    }

    private fun initListener() {
        fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setupAdapter() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel.todos.observe(this) { data ->
            adapter = TodoAdapter(
                todos = data,
                onEdit = { showEditDialog(it) },
                onDelete = { todoViewModel.deleteTodo(it) }
            )
            recyclerView.adapter = adapter
        }
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)
    }

    private fun showAddDialog() {
        AddEditDialog(this, onSave = { title ->
            todoViewModel.addTodo(title)
        }).show()
    }

    private fun showEditDialog(todo: Todo) {
        AddEditDialog(this, todo.title ?: "", onSave = { title ->
            todo.title = title
            todoViewModel.updateTodo(todo)
        }).show()
    }
}