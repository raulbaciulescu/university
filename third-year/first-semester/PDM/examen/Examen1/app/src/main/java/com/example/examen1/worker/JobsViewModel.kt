package com.example.examen1.worker

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.*
import kotlinx.coroutines.launch

data class JobUiState(val text: String = "worker")

class JobsViewModel(application: Application) : AndroidViewModel(application) {
    var uiState by mutableStateOf(JobUiState())
        private set
    private var workManager: WorkManager = WorkManager.getInstance(getApplication())

    init {
        startJob()
    }

    private fun startJob() {
        viewModelScope.launch{
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            //(15, TimeUnit.MINUTES)
            val myWork = OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(constraints)
                .build()
            workManager.apply {
                enqueue(myWork)
            }
        }
    }


    companion object {
        fun Factory(application: Application): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JobsViewModel(application)
            }
        }
    }
}
