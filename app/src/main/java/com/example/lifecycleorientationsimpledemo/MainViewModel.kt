package com.example.lifecycleorientationsimpledemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val counter: Int = 0,
    val lifecycleLogs: List<String> = emptyList(),
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun logLifecycleEvent(event: String) {
        _uiState.update { currentState ->
            currentState.copy(
                lifecycleLogs = currentState.lifecycleLogs + event,
            )
        }
    }

    fun incrementCounter() {
        _uiState.update { currentState ->
            currentState.copy(counter = currentState.counter + 1)
        }
    }

    fun decrementCounter() {
        _uiState.update { currentState ->
            currentState.copy(counter = currentState.counter - 1)
        }
    }

    fun resetCounter() {
        _uiState.update { currentState ->
            currentState.copy(counter = 0)
        }
    }


    fun clearLogs() {
        _uiState.update { currentState ->
            currentState.copy(lifecycleLogs = emptyList())
        }
    }
}
