package ca.quantum.quants.it.housefy.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// feedbackValidation.kt

fun validateInput(
    input: String,
    errorConditions: List<Pair<(String) -> Boolean, String>>,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    dismiss: String
): Boolean {
    for ((condition, errorMessage) in errorConditions) {
        if (condition(input)) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = dismiss,
                    duration = SnackbarDuration.Short
                )
            }
            return false
        }
    }
    return true
}
