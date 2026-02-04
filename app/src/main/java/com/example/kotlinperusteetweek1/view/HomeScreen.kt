package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinperusteetweek1.model.Task
import com.example.kotlinperusteetweek1.viewModel.DetailDialog
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel = viewModel(),
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {}
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()

    var showDoneOnly by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    val visibleTasks = remember(tasks, showDoneOnly) {
        if (showDoneOnly) {
            tasks.filter { it.done }
        } else {
            tasks
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text("Tehtävälista") },
            actions = {
                IconButton(onClick = onNavigateCalendar) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Go to Calendar"
                    )
                }
            }
        )

        Row(
            onClick = onAddClick,
            modifier = Modifier.padding(8.dp),
        ) {
            Text("add Task")
        }

        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp),
        ) {
            Text("active")
        }
    }


    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onClose = { viewModel.closeDialog() },
            onUpdate = { viewModel.updateTask(it) },
            onDelete = { viewModel.removeTask(task.id) }
        )
    }
}
