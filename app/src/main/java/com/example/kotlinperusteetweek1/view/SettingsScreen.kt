package com.example.kotlinperusteetweek1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val accent = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CenterAlignedTopAppBar(
            title = { Text("Asetukset") },
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Ulkoasu",
                style = MaterialTheme.typography.titleMedium
            )

            Button(
                onClick = onToggleTheme,
                colors = ButtonDefaults.buttonColors(
                    containerColor = accent,
                    contentColor = Color.White
                )
            ) {
                Text(
                    if (darkTheme) "Vaihda vaaleaan teemaan"
                    else "Vaihda tummaan teemaan"
                )
            }
        }
    }
}
