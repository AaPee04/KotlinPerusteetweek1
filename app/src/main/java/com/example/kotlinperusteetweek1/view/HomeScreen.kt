package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinperusteetweek1.viewModel.DetailDialog
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {}
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val showAddDialog by viewModel.showAddDialog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tehtävälista") },
                actions = {
                    IconButton(onClick = onNavigateCalendar) {
                        Icon(Icons.Filled.CalendarMonth, contentDescription = "Calendar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(8.dp)
        ) {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.openTask(task.id) }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            viewModel.toggleDone(task.id)
                        }
                    )
                    Column {
                        Text(task.title)
                        Text(task.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddDialog(
            onDismiss = { viewModel.closeAddDialog() },
            onSave = { title, description ->
                viewModel.addTask(title, description)
                viewModel.closeAddDialog()
            }
        )
    }

    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onClose = { viewModel.closeEditDialog() },
            onUpdate = { viewModel.updateTask(it) },
            onDelete = { viewModel.removeTask(task.id) }
        )
    }
}
