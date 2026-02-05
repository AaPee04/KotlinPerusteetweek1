package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {},
    onNavigateSettings: () -> Unit = {}
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val showAddDialog by viewModel.showAddDialog.collectAsState()

    var hideUndone by remember { mutableStateOf(false) }

    val visibleTasks = if (hideUndone) {
        tasks.filter { it.done }
    } else {
        tasks
    }


    val accent = MaterialTheme.colorScheme.primary

    Scaffold( // Napit navigointia varten
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Tehtävät")
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateSettings) {
                        Icon(
                            imageVector = Icons.Filled.Settings, // SettinsScreen
                            contentDescription = "Settings",
                            tint = accent
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateCalendar) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth, // CalendarScreen
                            contentDescription = "Calendar",
                            tint = accent
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
                        containerColor = accent,
                        contentColor = Color.White
                    )
                ) {
                    Text("➕ Lisää tehtävä") // Lisää tehtävä kutsuu TaskDialogissa AddTask
                }

                OutlinedButton(
                    onClick = { hideUndone = !hideUndone },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = accent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(accent)
                    )
                ) {
                    Text(
                        if (hideUndone) "Näytä tekemättömät"
                        else "Piilota tekemättömät" // Piilottaa tekemättömät ja tuo ne takas
                    )
                }

            }

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
                                    checkedColor = accent,
                                    uncheckedColor = accent,
                                    checkmarkColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) { // AddTaskin kutsu
        TaskDialog(
            task = null,
            onDismiss = { viewModel.closeAddDialog() },
            onSave = { title, description, dueDate ->
                viewModel.addTask(title, description, dueDate)
                viewModel.closeAddDialog()
            }
        )
    }


    selectedTask?.let { task -> // Taskin muokkauksen kutsu
        TaskDialog(
            task = task,
            onDismiss = { viewModel.closeEditDialog() },
            onSave = { title, description, dueDate ->
                viewModel.updateTask(
                    task.copy(
                        title = title,
                        description = description,
                        dueDate = dueDate
                    )
                )
            },
            onDelete = {
                viewModel.removeTask(task.id)
            }
        )
    }

}
