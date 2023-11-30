package ca.quantum.quants.it.housefy.network

import android.util.Log
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


suspend fun fetchLightStatus(): Boolean {
    return client.get("https://housefy-backend-app.azurewebsites.net/api/light")
}

suspend fun updateLightStatus(isLightOn: Boolean): Boolean {
    return try {
        val response: HttpResponse =
            client.post("https://housefy-backend-app.azurewebsites.net/api/light") {
                contentType(ContentType.Application.Json)
                body = isLightOn
            }

        response.status == HttpStatusCode.OK
    } catch (e: Exception) {
        Log.e("Light Update", "Error updating light status: ${e.localizedMessage}")
        false
    }
}