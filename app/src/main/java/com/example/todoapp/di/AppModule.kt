package com.example.todoapp.di

import com.example.todoapp.database.FirebaseDatabase
import com.example.todoapp.database.FirebaseDatabaseImpl
import com.example.todoapp.repository.ToDoRepositoryImpl
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.TodoViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//provide dependencies
val appModule = module {
    single {
        Retrofit.Builder().baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
    single { Firebase.firestore }
    single<FirebaseDatabase> {
        FirebaseDatabaseImpl(get())
    }
    single<TodoRepository> {
        ToDoRepositoryImpl(get())
    }
    viewModel {
        TodoViewModel(get())
    }
}