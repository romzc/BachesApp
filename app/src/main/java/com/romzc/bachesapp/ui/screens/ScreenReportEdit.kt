package com.romzc.bachesapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenReportEdit(navController: NavController) {
    val showDialog = remember{ mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        title = { Text(text = "Título del diálogo") },
        text = { Text(text = "Contenido del diálogo") },
        confirmButton = {
            Button(
                onClick = { showDialog.value = false },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                    navController.popBackStack()
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}