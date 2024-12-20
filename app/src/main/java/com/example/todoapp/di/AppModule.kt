package com.example.todoapp.di

import com.example.todoapp.database.FirebaseDatabase
import com.example.todoapp.database.FirebaseDatabaseImpl
import com.example.todoapp.repository.ToDoRepositoryImpl
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.CreateTodoViewModel
import com.example.todoapp.ui.TodoListViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//provide dependencies
val appModule = module {
    single { Firebase.firestore }
    single<FirebaseDatabase> {
        FirebaseDatabaseImpl(get())
    }
    single<TodoRepository> {
        ToDoRepositoryImpl(get())
    }
    viewModel {
        TodoListViewModel(get())
    }
    viewModel {
        CreateTodoViewModel(get())
    }
}