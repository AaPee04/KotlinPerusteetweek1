package com.example.kotlinperusteetweek1.model

fun addTask(list: List<Task>, newTask: Task): List<Task> {
    return list + newTask
}

fun toggleDone(list: List<Task>, taskId: Int): List<Task> {
    return list.map { task ->
        if (task.id == taskId) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun removeTask(list: List<Task>, taskId: Int): List<Task> {
    return list.filterNot { task ->
        task.id == taskId
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}