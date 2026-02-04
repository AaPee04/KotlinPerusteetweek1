package com.example.kotlinperusteetweek1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.example.kotlinperusteetweek1.view.HomeScreen
import androidx.compose.material3.Surface
import com.example.kotlinperusteetweek1.ui.theme.MyAppTheme
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val tag = "MainActivity"
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()


            MyAppTheme {
                Scaffold() {
                    NavHost(
                        navController = navController
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                viewModel = viewModel
                                onTaskClick = { id ->
                                    viewModel.openTask(id)
                                },
                                onAddClick = {
                                    viewModel.addTaskDialogVisible.value = true
                                },
                                onNavigateCalendar = {
                                    navController.navigate("calendar")
                                }

                            )
                        }
                        composable("calendar") {
                            CalendarScreen(
                                viewModel = viewModel
                                        onTaskClick = { id ->
                                    viewModel.openTask(id)
                                },
                                onNavigateHome = {
                                    navController.navigate("home")
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}