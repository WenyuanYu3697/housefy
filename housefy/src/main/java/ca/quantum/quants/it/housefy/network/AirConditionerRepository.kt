package ca.quantum.quants.it.housefy.network

import android.util.Log
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class AirConditionerStatus(
    var isOn: Boolean,
    var speed: Int
)

suspend fun fetchAirConditionerStatus(): AirConditionerStatus {
    return client.get("https://housefybackend.azurewebsites.net/api/ac-fan")
}

suspend fun updateAirConditionerStatus(
    isAirConditionerOn: Boolean,
    airConditionerSpeed: Int
): Boolean {
    return try {
        val requestBody =
            AirConditionerStatus(isOn = isAirConditionerOn, speed = airConditionerSpeed)

        val jsonPayload = Json.encodeToString(requestBody)
        Log.d("AirConditioner Update", "Sending payload: $jsonPayload")

        val response: HttpResponse =
            client.post("https://housefybackend.azurewebsites.net/api/ac-fan") {
                contentType(ContentType.Application.Json)
                body = requestBody
            }

        Log.d("AirConditioner Update", "Response: $response")

        response.status == HttpStatusCode.OK
    } catch (e: Exception) {
        Log.e(
            "AirConditioner Update",
            "Error updating air conditioner status: ${e.localizedMessage}"
        )
        false
    }
}