package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel
import org.jetbrains.annotations.ApiStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: TaskViewModel = viewModel(),
    onTaskClick: (Int) -> Unit = {},
    onNavigateHome: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()

    val grouped = tasks.groupBy { it.dueDate ?: "No date"}

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(

        )
    }
}