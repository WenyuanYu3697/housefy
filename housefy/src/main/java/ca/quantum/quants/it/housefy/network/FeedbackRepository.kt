package ca.quantum.quants.it.housefy.network

// NetworkRepository.kt

import ca.quantum.quants.it.housefy.models.Feedback
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun postFeedback(feedback: Feedback): Pair<Boolean, String> {
    val client = HttpClient {}

    val json = Json { ignoreUnknownKeys = true }
    val jsonData = json.encodeToString(feedback)

    return try {
        println("jsonData: $jsonData")
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
