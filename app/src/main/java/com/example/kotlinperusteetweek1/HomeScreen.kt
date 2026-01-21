package com.example.kotlinperusteetweek1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinperusteetweek1.domain.Task

@Composable
fun HomeScreen(
    viewModel: TaskViewModel = viewModel()
) {
    var newTaskTitle by remember { mutableStateOf("") }
    var showDoneOnly by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Uusi tehtävä") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                id = viewModel.tasks.size + 1,
                                title = newTaskTitle,
                                description = "",
                                priority = 1,
                                dueDate = "2026.2.28",
                                done = false
                            )
                        )
                        newTaskTitle = ""
                    }
                }
            ) { Text("Lisää") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { showDoneOnly = !showDoneOnly }) {
                Text(if (showDoneOnly) "Näytä kaikki" else "Näytä valmiit")
            }
            Button(onClick = { viewModel.sortTasksByDueDate() }) {
                Text("Järjestä eräpäivän mukaan")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val tasksToShow = if (showDoneOnly) viewModel.getTasksByDone(true) else viewModel.tasks

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = tasksToShow, key = { it.id }) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )
                    Text(text = "${task.title} - ${task.dueDate}", modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.removeTask(task.id) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Poista")
                    }
                }
            }
        }
    }
}
