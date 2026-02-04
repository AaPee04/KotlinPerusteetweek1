package com.example.kotlinperusteetweek1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.example.kotlinperusteetweek1.view.HomeScreen
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinperusteetweek1.navigation.ROUTE_CALENDAR
import com.example.kotlinperusteetweek1.navigation.ROUTE_HOME
import com.example.kotlinperusteetweek1.ui.theme.MyAppTheme
import com.example.kotlinperusteetweek1.view.CalendarScreen
import com.example.kotlinperusteetweek1.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            MyAppTheme {
                Scaffold { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME,
                        modifier = Modifier.padding(padding)
                    ) {

                        composable(ROUTE_HOME) {
                            HomeScreen(
                                viewModel = viewModel,
                                onTaskClick = { id ->
                                    viewModel.openTask(id)
                                },
                                onAddClick = {
                                    viewModel.openAddDialog()
                                },
                                onNavigateCalendar = {
                                    navController.navigate(ROUTE_CALENDAR)
                                }
                            )
                        }

                        composable(ROUTE_CALENDAR) {
                            CalendarScreen(
                                viewModel = viewModel,
                                onTaskClick = { id ->
                                    viewModel.openTask(id)
                                },
                                onNavigateHome = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}