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

    fun addTask(task: Task) {
        _tasks.value = addTask(_tasks.value, task)
    }

    fun toggleDone(taskId: Int) {
        _tasks.value = toggleDone(_tasks.value, taskId)
    }

    fun removeTask(taskId: Int) {
        _tasks.value = removeTask(_tasks.value, taskId)
        closeDialog()
    }

    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        closeDialog()
    }

    fun getTasksByDone(done: Boolean): List<Task> {
        return filterByDone(_tasks.value, done)
    }

    fun sortTasksByDueDate() {
        _tasks.value = sortByDueDate(_tasks.value)
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun closeDialog() {
        _selectedTask.value = null
    }
}
