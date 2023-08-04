package ca.quantum.quants.it.housefy.network

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

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
            Pair(true, "Thank you for your feedback.")
        } else {
            Pair(false, "Error: ${response.status.description}")
        }
    } catch (e: Exception) {
        Pair(false, "Exception in postFeedback: ${e.localizedMessage}")
    } finally {
        client.close()
    }
}
