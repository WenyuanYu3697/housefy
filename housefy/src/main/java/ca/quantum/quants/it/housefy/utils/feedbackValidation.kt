package ca.quantum.quants.it.housefy.utils

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
