package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit,
    onNavigateHome: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()

    var ascending by remember { mutableStateOf(true) }
    val accent = MaterialTheme.colorScheme.primary

    val groupedTasks = tasks
        .groupBy { it.dueDate ?: "No date" }
        .toSortedMap(
            if (ascending) compareBy { it }
            else compareByDescending { it }
        )

    Column(modifier = Modifier.fillMaxSize()) {

        CenterAlignedTopAppBar(
            title = {
                Text("Kalenteri")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateHome) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = "Back",
                        tint = accent
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { ascending = !ascending },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = accent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(accent)
                )
            ) {
                Text(
                    if (ascending) // DueDate sort
                        "Järjestä: uusin → vanhin"
                    else
                        "Järjestä: vanhin → uusin"
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            groupedTasks.forEach { (date, tasksForDate) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = accent
                    )
                }

                items(tasksForDate) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTaskClick(task.id) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {

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

                            if (task.done) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "✓ Valmis",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = accent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    selectedTask?.let { task ->
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
