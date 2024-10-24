package com.example.todoapp.repository

import com.example.todoapp.database.FirebaseDatabase
import com.example.todoapp.model.TodoItem
import com.google.firebase.Timestamp

class ToDoRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : TodoRepository {
    override suspend fun getAllTodoItems(): Result<List<TodoItem>> {
        return try {
            val result = firebaseDatabase.getAllTodoItems()
            if (result.isSuccess) {
                val todos = result.getOrNull()?.map { dataMap ->
                    val name = dataMap["name"] as String
                    val date = dataMap["date"] as Timestamp
                    val isDone = dataMap["isDone"] as Boolean
                    TodoItem(name, date, isDone)
                } ?: emptyList()
                Result.success(todos)
            } else {
                Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
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