import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackPage() {
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }
    var comment by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Rating")

            RatingBar(value = rating, onValueChange = { rating = it })

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Comment") },
                modifier = Modifier.fillMaxWidth()
                .height(150.dp),
                maxLines = 10
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Handle submission logic here */ }) {
                Text("Submit Feedback")
            }
        }
    }
}

@Composable
fun RatingBar(value: Float, onValueChange: (Float) -> Unit) {
    Row {
        for (i in 1..5) {
            IconButton(onClick = { onValueChange(i.toFloat()) }, modifier = Modifier.size(48.dp)) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Star),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = if (value >= i) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackPagePreview() {
    FeedbackPage()
}
