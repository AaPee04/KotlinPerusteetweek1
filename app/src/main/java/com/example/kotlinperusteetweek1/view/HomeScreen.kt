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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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

    var hideDone by remember { mutableStateOf(false) }

    val visibleTasks = if (hideDone) {
        tasks.filter { !it.done }
    } else {
        tasks
    }

    val red = Color.Red

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Tehtävät")
                },
                actions = {
                    IconButton(onClick = onNavigateCalendar) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = "Calendar",
                            tint = red
                        )
                    }
                }
            )
        }
    )
    { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            // 🔘 ACTION BUTTONS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = red,
                        contentColor = Color.White
                    )
                ) {
                    Text("➕ Lisää tehtävä")
                }

                OutlinedButton(
                    onClick = { hideDone = !hideDone },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = red
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(red)
                    )
                ) {
                    Text(
                        if (hideDone) "Näytä tehdyt"
                        else "Piilota tehdyt"
                    )
                }
            }

            // 📋 TASK LIST
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(visibleTasks) { task ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.openTask(task.id) }
                            .alpha(if (task.done) 0.5f else 1f),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                if (task.description.isNotBlank()) {
                                    Text(
                                        text = task.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = task.dueDate ?: "",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }

                            Checkbox(
                                checked = task.done,
                                onCheckedChange = {
                                    viewModel.toggleDone(task.id)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = red,
                                    uncheckedColor = red,
                                    checkmarkColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    // ➕ ADD TASK DIALOG
    if (showAddDialog) {
        AddDialog(
            onDismiss = { viewModel.closeAddDialog() },
            onSave = { title, description ->
                viewModel.addTask(title, description)
                viewModel.closeAddDialog()
            }
        )
    }

    // ✏️ EDIT TASK DIALOG
    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onClose = { viewModel.closeEditDialog() },
            onUpdate = { viewModel.updateTask(it) },
            onDelete = { viewModel.removeTask(task.id) }
        )
    }
}
