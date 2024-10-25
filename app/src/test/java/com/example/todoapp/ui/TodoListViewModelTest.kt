package com.example.todoapp.ui

import com.example.todoapp.model.TodoItem
import com.example.todoapp.model.TodoItemUI
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.model.TodoItemUiState
import com.google.firebase.Timestamp
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

@OptIn(ExperimentalCoroutinesApi::class)
class TodoListViewModelTest {

    @InjectMockKs
    private lateinit var sut: TodoListViewModel

    @MockK
    private lateinit var todoRepository: TodoRepository

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllItems - should get all todo items on success`() = runTest {
        //Given
        val localDateTime = LocalDateTime.of(2024, 8, 10, 0, 0)
        val date = Timestamp(localDateTime.toInstant(ZoneOffset.UTC))
        val todos = listOf(TodoItem(name = "Todo1", date = date, isDone = false))

        coEvery { todoRepository.getAllTodoItems() } returns Result.success(todos)

        //When
        sut.getAllTodoItems()

        //Then
        val expected = TodoItemUiState.Success(
            listOf(
                TodoItemUI(
                    name = "Todo1",
                    date = "10.08.24",
                    isDone = false
                )
            )
        )
        val actual = sut.todoItemsUiState
            .dropWhile { it is TodoItemUiState.Loading }
            .first()

        assertEquals(expected, actual)
    }
}