package com.example.todoapp.model

import com.google.firebase.Timestamp

data class TodoItem(val name :String, val date :Timestamp, val isDone :Boolean)
