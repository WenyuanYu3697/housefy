package ca.quantum.quants.it.housefy.network

import ca.quantum.quants.it.housefy.models.EnvironmentData
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

val client = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 3000
    }
}

suspend fun fetchEnvironmentData(): EnvironmentData? = try {
    withContext(Dispatchers.IO) {
        client.get("https://housefy-backend-app.azurewebsites.net/api/environment")
    }
} catch (e: Exception) {
    null
}
