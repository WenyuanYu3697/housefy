package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.R
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.http.content.TextContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackPage() {
    var fullName by remember { mutableStateOf("") }
    val phoneModel by remember { mutableStateOf(Build.MODEL) }  // automatically get phone model
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(1) }  // rating as Int
    var comment by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    var dialogVisible by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dismiss = stringResource(id = R.string.dismiss_label)
    val fullNameError = stringResource(id = R.string.fullname_error)
    val fullNameError1 = stringResource(id = R.string.fullname_error1)

    fun clearFields() {
        fullName = ""
        phoneNumber = ""
        email = ""
        rating = 1
        comment = ""
    }

    fun showSnackbarMessage(message: String, dismiss: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = dismiss,
                duration = SnackbarDuration.Short
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F2F1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 50.dp, 16.dp, 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text(stringResource(R.string.full_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(stringResource(R.string.phone_number)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text(stringResource(R.string.comment)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 10
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                RatingBar(current = rating, onValueChange = { newRating ->
                    rating = newRating
                })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    when {
                        fullName.length > 50 -> {
                            showSnackbarMessage(fullNameError, dismiss)
                        }
                        fullName.any { it.isDigit() } -> {
                            showSnackbarMessage(fullNameError1, dismiss)
                        }
                        else -> {
                            coroutineScope.launch(Dispatchers.IO) {
                                try {
                                    val user = User(fullName, email, phoneModel, phoneNumber)
                                    val feedback = Feedback(rating, comment, user)
                                    val result = postFeedback(feedback)
                                    dialogMessage = result.second
                                    dialogVisible = true
                                    if (result.first) {
                                        // Call the clearFields() function after successful submission
                                        clearFields()
                                    }
                                } catch (e: Exception) {
                                    dialogMessage = "Error: ${e.localizedMessage}"
                                    dialogVisible = true
                                }
                            }
                        }
                    }
                }) {
                    Text(stringResource(R.string.submit_feedback))
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }

    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false },
            title = { Text(text = stringResource(R.string.feedback_response)) },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                Button(onClick = { dialogVisible = false }) {
                    Text("Ok")
                }
            }
        )
    }
}

@Serializable
data class User(
    val FullName: String,
    val Email: String,
    val PhoneModel: String,
    val PhoneNumber: String
)

@Serializable
data class Feedback(
    val Rate: Int,
    val Comment: String,
    val User: User
)

suspend fun postFeedback(feedback: Feedback): Pair<Boolean, String> {
    val client = HttpClient {}

    val json = Json { ignoreUnknownKeys = true }
    val jsonData = json.encodeToString(feedback)

    return try {
        val response: HttpResponse =
            client.post("https://housefybackend.azurewebsites.net/api/feedback") {
                body = TextContent(jsonData, ContentType.Application.Json)
            }

        if (response.status == HttpStatusCode.OK) {
            Pair(true, "Feedback submitted successfully.")
        } else {
            Pair(false, "Failed to submit feedback: ${response.status.description}")
        }
    } catch (e: Exception) {
        Pair(false, "Exception in postFeedback: ${e.localizedMessage}")
    } finally {
        client.close()
    }
}

@Composable
fun RatingBar(current: Int, onValueChange: (Int) -> Unit) {
    var rating by remember { mutableStateOf(current) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            val isFilled = index < rating

            Icon(
                imageVector = if (isFilled) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)  // increased size
                    .clickable {
                        rating = index + 1
                        onValueChange(rating)
                    }
            )
        }
    }
}
