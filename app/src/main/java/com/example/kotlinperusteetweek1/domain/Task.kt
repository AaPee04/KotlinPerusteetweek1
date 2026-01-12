package com.example.kotlinperusteetweek1.domain

import java.time.LocalDate

enum class Priority {
    LOW, MEDIUM, HIGH
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: LocalDate,
    val done: Boolean
)

val mockTasks = listOf(
    Task(
        id = 1,
        title = "Harjoittele Kotlin",
        description = "Opettele Kotlin kieltä",
        priority = Priority.MEDIUM,
        dueDate = LocalDate.now().plusDays(1),
        done = false
    ),
    Task(
        id = 2,
        title = "Kirjoita Raportti",
        description = "Kirjoita raportti kurssia varten",
        priority = Priority.HIGH,
        dueDate = LocalDate.now().plusDays(3),
        done = false
    ),
    Task(
        id = 3,
        title = "Opettele Typescripitä",
        description = "Opettele typescriptiä kurssia varten",
        priority = Priority.MEDIUM,
        dueDate = LocalDate.now(),
        done = true
    ),
    Task(
        id = 4,
        title = "Tee ruokaa",
        description = "Tee ruokaa tälle päivälle",
        priority = Priority.LOW,
        dueDate = LocalDate.now().plusWeeks(1),
        done = false
    ),
    Task(
        id = 5,
        title = "Lue kirja",
        description = "Lue tai Kuuntele jokin kirja",
        priority = Priority.LOW,
        dueDate = LocalDate.now().plusDays(2),
        done = true
    ),
    Task(
        id = 6,
        title = "Rakenna Lego",
        description = "Rakenna lego setti loppuun",
        priority = Priority.LOW,
        dueDate = LocalDate.now().plusDays(4),
        done = true
    ),
    Task(
        id = 7,
        title = "Palauta tehtävät",
        description = "Palauta eri koulu tehtävät",
        priority = Priority.HIGH,
        dueDate = LocalDate.now().plusDays(1),
        done = false
    )
)

fun addTask(list: List<Task>, task: Task): List<Task> {
    return list + task
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}

fun main() {

    var tasks = mockTasks

    println("Lajittelematon lista")
    println(tasks)

    val newTask = Task(
        id = 99,
        title = "Learn Kotlin",
        description = "Practice lists and functions",
        priority = Priority.HIGH,
        dueDate = LocalDate.now().plusDays(5),
        done = false
    )

    tasks = addTask(tasks, newTask)
    println("\nADD TASK - nappi painettu")
    println(tasks)

    tasks = toggleDone(tasks, id = 1)
    println("\nTOGGLE DONE (id=1) - nappi painettu")
    println(tasks)

    val doneTasks = filterByDone(tasks, done = true)
    println("\nFILTER DONE = true - nappi painettu")
    println(doneTasks)

    val sortedTasks = sortByDueDate(tasks)
    println("\nSORT BY DUE DATE - nappi painettu")
    println(sortedTasks)
}