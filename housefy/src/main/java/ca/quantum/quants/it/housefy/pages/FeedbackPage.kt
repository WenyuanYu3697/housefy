package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.os.Build
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.R
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ca.quantum.quants.it.housefy.network.postFeedback
import ca.quantum.quants.it.housefy.models.User
import ca.quantum.quants.it.housefy.models.Feedback
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.Purple
import ca.quantum.quants.it.housefy.utils.validateInput
import kotlinx.coroutines.withContext

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
    var loadingDialogVisible by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dismiss = stringResource(id = R.string.dismiss_label)
    val fullNameError = stringResource(id = R.string.fullname_error)
    val fullNameError1 = stringResource(id = R.string.fullname_error1)
    val fullNameError2 = stringResource(id = R.string.fullname_error2)
    val emailError = stringResource(id = R.string.email_error)
    val emailError1 = stringResource(id = R.string.email_error1)
    val emailError2 = stringResource(id = R.string.email_error2)
    val phoneNumebrError = stringResource(id = R.string.phoneNumber_error)
    val phoneNumberError1 = stringResource(id = R.string.phoneNumber_error1)
    val phoneNumberError2 = stringResource(id = R.string.phoneNumber_error2)
    val commentError = stringResource(id = R.string.comment_error)
    val commentError1 = stringResource(id = R.string.comment_error1)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundGrey
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 50.dp, 16.dp, 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            CustomOutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = stringResource(R.string.full_name),
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = stringResource(R.string.full_name)
                    )
                }
            )

            CustomOutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = stringResource(R.string.phone_number),
                leadingIcon = {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = stringResource(R.string.phone_number)
                    )
                }
            )

            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.email),
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = stringResource(R.string.email)
                    )
                }
            )

            CustomOutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = stringResource(R.string.comment),
                height = 150.dp,
                maxLines = 5,
                leadingIcon = {
                    Icon(
                        Icons.Default.Comment,
                        contentDescription = stringResource(R.string.comment)
                    )
                }
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                RatingBar(current = rating, onValueChange = { newRating ->
                    rating = newRating
                })
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (
                            validateInput(
                                fullName,
                                listOf(
                                    { s: String -> s.isEmpty() } to fullNameError2,
                                    { s: String -> s.length > 50 } to fullNameError,
                                    { s: String -> s.any { char -> char.isDigit() } } to fullNameError1
                                ),
                                coroutineScope, snackbarHostState, dismiss
                            ) &&
                            validateInput(
                                email,
                                listOf(
                                    { s: String -> s.isEmpty() } to emailError2,
                                    { s: String -> s.length > 100 } to emailError,
                                    { s: String ->
                                        !Patterns.EMAIL_ADDRESS.matcher(s).matches()
                                    } to emailError1
                                ),
                                coroutineScope, snackbarHostState, dismiss
                            ) &&
                            validateInput(
                                phoneNumber,
                                listOf(
                                    { s: String -> s.isEmpty() } to phoneNumberError2,
                                    { s: String -> s.length > 30 } to phoneNumebrError,
                                    { s: String -> s.any { char -> char !in '0'..'9' } } to phoneNumberError1,
                                ),
                                coroutineScope, snackbarHostState, dismiss
                            ) &&
                            validateInput(
                                comment,
                                listOf(
                                    { s: String -> s.isEmpty() } to commentError1,
                                    { s: String -> s.length > 200 } to commentError
                                ),
                                coroutineScope, snackbarHostState, dismiss
                            )


                        ) {
                            // All fields have been validated and passed.
                            coroutineScope.launch(Dispatchers.IO) {
                                try {
                                    withContext(Dispatchers.Main) {
                                        loadingDialogVisible = true
                                    } // Show loading dialog
                                    val user = User(fullName, email, phoneModel, phoneNumber)
                                    val feedback = Feedback(rating, comment, user)
                                    val result = postFeedback(feedback)
                                    withContext(Dispatchers.Main) {
                                        loadingDialogVisible = false
                                        if (result.first) {
                                            fullName = ""
                                            phoneNumber = ""
                                            email = ""
                                            comment = ""
                                            rating = 1
                                        }
                                        dialogMessage = result.second
                                        dialogVisible = true
                                    }
                                } catch (e: Exception) {
                                    withContext(Dispatchers.Main) {
                                        loadingDialogVisible = false
                                        dialogMessage = "Error: ${e.localizedMessage}"
                                        dialogVisible = true
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(stringResource(R.string.submit_feedback))
                }
            }
        }
        if (loadingDialogVisible) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Please wait...") },
                text = { CircularProgressIndicator() },
                confirmButton = {}
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(bottom = 10.dp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
    height: Dp = Dp.Unspecified,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(color = Color.White, shape = RoundedCornerShape(4.dp)
    ),
    maxLines = maxLines,
    leadingIcon = leadingIcon,
    textStyle = LocalTextStyle.current.copy(color = Color.Black),
    )
    Spacer(modifier = Modifier.height(16.dp))
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
                    },
                tint = if (isFilled) Purple else Color.Gray
            )
        }
    }
}