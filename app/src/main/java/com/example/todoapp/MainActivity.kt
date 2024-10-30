package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.CreateTodoViewModel
import com.example.todoapp.ui.screens.ToDoListScreen
import com.example.todoapp.ui.theme.DarkGreen
import com.example.todoapp.ui.theme.LightGreen
import com.example.todoapp.ui.theme.White
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var createTodoViewModel: CreateTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            createTodoViewModel = getViewModel()

            Scaffold(
                containerColor = White,
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.app_name_scaffold)) },
                        colors = topAppBarColors(
                            containerColor = LightGreen,
                            titleContentColor = DarkGreen,
                        ),
                        actions = {
                            IconButton(
                                onClick = {
                                    createTodoViewModel.showBottomSheet(true)
                                },
                                colors = IconButtonColors(
                                    containerColor = DarkGreen,
                                    contentColor = LightGreen,
                                    disabledContentColor = LightGreen,
                                    disabledContainerColor = LightGreen,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = "Add a Todo Item",
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(25.dp)
                ) {
                    ToDoListScreen()
                    CreateTodoScreen()
                }
            }
        }
    }

    @Composable
    fun CreateTodoScreen() {
        val showCreateModal = createTodoViewModel.showBottomSheet.collectAsState()
        var todoText by rememberSaveable { mutableStateOf("")  }

        if (showCreateModal.value) {
            ModalBottomSheet(
                onDismissRequest = { createTodoViewModel.showBottomSheet(false) },
                containerColor = White
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        label = { Text("Todo") },
                        value = todoText,
                        onValueChange = {newValue -> todoText = newValue},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    FilledTonalButton(
                        onClick = {
                            createTodoViewModel.createTodoItem(todoText)
                            createTodoViewModel.showBottomSheet(false)
                                  },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonColors(
                            containerColor = LightGreen,
                            contentColor = DarkGreen,
                            disabledContentColor = DarkGreen,
                            disabledContainerColor = LightGreen
                        )
                    ) {
                        Text("Create")
                    }
                }
            }
        }
    }
}


