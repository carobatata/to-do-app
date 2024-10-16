package com.example.todoapp.repository

import android.util.Log
import com.example.todoapp.di.MyApi
import com.example.todoapp.model.TodoItem
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ToDoRepositoryImpl(private val api: MyApi) :TodoRepository{

    private val db: FirebaseFirestore = Firebase.firestore

//    override fun doNetworkCall() {
////        api.callApi()
//        println("Hi")
//    }

    override suspend fun getAllTodoItems(): List<TodoItem> {
        return try {
            db.collection("todolist")
                .get()
                .await()
                .mapNotNull { snapshot ->
                    snapshot.toObject(
                        TodoItem::class.java
                    )
                }
        } catch (e: Exception) {
            Log.i("TodoListApp", e.toString())
            emptyList()
        }
    }

    fun createTodoItem() {
        val dataBase = Firebase.firestore
        val todoItem = TodoItem("Nuevo Item", Timestamp.now())
        dataBase.collection("todolist")
            .add(todoItem)
            .addOnSuccessListener {
                Log.i("TodoListApp", "Success loading ${todoItem.name}")
            }
            .addOnFailureListener {
                Log.i("TodoListApp", "Error loading ${todoItem.name}")
            }
    }
}