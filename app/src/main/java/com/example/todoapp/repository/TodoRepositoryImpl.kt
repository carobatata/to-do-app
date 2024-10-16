package com.example.todoapp.repository

import com.example.todoapp.di.MyApi
import com.example.todoapp.model.TodoItem

class ToDoRepositoryImpl(private val api: MyApi) :TodoRepository {
//    override fun doNetworkCall() {
////        api.callApi()
//        println("Hi")
//    }

    override fun getAllTodoItems(): List<TodoItem> {
        return listOf(
            TodoItem("Book esterlization for Canela", "14 oct 2024"),
            TodoItem("Hire Canela insurance", "10 oct 2024"),
            TodoItem("Hire new internet provider", "08 sept 2024"),
        )
    }
}