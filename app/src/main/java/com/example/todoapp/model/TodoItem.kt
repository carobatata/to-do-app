package com.example.todoapp.model

import com.google.firebase.Timestamp

data class TodoItem(val name:String? = null, val date: Timestamp? = null, val isDone:Boolean = false)
