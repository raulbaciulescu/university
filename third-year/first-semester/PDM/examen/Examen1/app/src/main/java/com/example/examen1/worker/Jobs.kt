package com.example.examen1.worker

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Jobs() {
    val jobsViewModel = viewModel<JobsViewModel>(
        factory = JobsViewModel.Factory(
            LocalContext.current.applicationContext as Application
        )
    )

    Text(
        "${jobsViewModel.uiState}",
    )
}
