package com.example.kotlinperusteetweek1.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.kotlinperusteetweek1.model.*

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(mockData)
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog

    private var nextId = mockData.maxOf { it.id } + 1

    fun addTask(title: String, description: String, dueDate: String) {
        val newTask = Task(
            id = nextId++,
            title = title,
            description = description,
            priority = 1,
            dueDate = dueDate,
            done = false
        )
        _tasks.value = _tasks.value + newTask
    }

    fun openAddDialog() {
        _showAddDialog.value = true
    }

    fun closeAddDialog() {
        _showAddDialog.value = false
    }

    fun openTask(taskId: Int) {
        _selectedTask.value = _tasks.value.find { it.id == taskId }
    }

    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        closeEditDialog()
    }

    fun removeTask(taskId: Int) {
        _tasks.value = removeTask(_tasks.value, taskId)
        closeEditDialog()
    }

    fun toggleDone(taskId: Int) {
        _tasks.value = toggleDone(_tasks.value, taskId)
    }

    fun closeEditDialog() {
        _selectedTask.value = null
    }
}
