package ca.quantum.quants.it.housefy.network

import android.util.Log
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

suspend fun fetchAirQualityStatus(): Boolean {
    return client.get("https://housefybackend.azurewebsites.net/api/air-quality-fan")
}

suspend fun updateAirQualityStatus(isAirQualityOn: Boolean): Boolean {
    return try {
        val response: HttpResponse =
            client.post("https://housefybackend.azurewebsites.net/api/air-quality-fan") {
                contentType(ContentType.Application.Json)
                body = isAirQualityOn
            }

        response.status == HttpStatusCode.OK
    } catch (e: Exception) {
        Log.e("Light Update", "Error updating light status: ${e.localizedMessage}")
        false
    }
}