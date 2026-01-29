package com.example.kotlinperusteetweek1.viewModel

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.kotlinperusteetweek1.model.Task
import com.example.kotlinperusteetweek1.view.DetailScreen

@Composable
fun DetailDialog(
    task: Task,
    onClose: () -> Unit,
    onUpdate: (Task) -> Unit,
    onDelete: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Edit Task") },
        text = {
            DetailScreen(
                title = title,
                onTitleChange = { title = it },
                description = description,
                onDescriptionChange = { description = it },
                onSave = { onUpdate(task.copy(title = title, description = description)) },
                onDelete = onDelete
            )
        },
        confirmButton = {},
        dismissButton = {}
    )
}
