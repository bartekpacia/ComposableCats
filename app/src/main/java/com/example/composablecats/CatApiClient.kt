package com.example.composablecats

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable

@Serializable
data class CatImageData(
    val url: String,
    val id: String,
    val width: Int,
    val height: Int,
)

class CatApiClient {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val baseUrl = "https://api.thecatapi.com/v1"

    suspend fun getCats(limit: Int = 10): List<CatImageData> {
        return client.get("$baseUrl/images/search?limit=$limit").body()
    }

    suspend fun getCatById(id: String): CatImageData {
        return client.get("$baseUrl/images/$id").body()
    }
}
