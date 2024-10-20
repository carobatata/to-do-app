package com.example.todoapp.repository

import com.example.todoapp.database.FirebaseDatabase
import com.example.todoapp.model.TodoItem

class ToDoRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) :TodoRepository{

    override suspend fun getAllTodoItems(): Result<List<TodoItem>> {
        return firebaseDatabase.getAllTodoItems()
    }

//    fun createTodoItem() {
//        val dataBase = Firebase.firestore
//        val todoItem = TodoItem("Nuevo Item", Timestamp.now())
//        dataBase.collection("todolist")
//            .add(todoItem)
//            .addOnSuccessListener {
//                Log.i("TodoListApp", "Success loading ${todoItem.name}")
//            }
//            .addOnFailureListener {
//                Log.i("TodoListApp", "Error loading ${todoItem.name}")
//            }
//    }
}