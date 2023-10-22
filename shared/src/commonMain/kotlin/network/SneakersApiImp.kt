package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import model.Sneaker

/**
 * val client = OkHttpClient()
 *
 * val request = Request.Builder()
 * 	.url("https://sneaker-database-stockx.p.rapidapi.com/getproducts?keywords=jordan&limit=5")
 * 	.get()
 * 	.addHeader("X-RapidAPI-Key", "c6aed5dadcmshed9d8b5d495c307p185849jsn971e13b2bcc8")
 * 	.addHeader("X-RapidAPI-Host", "sneaker-database-stockx.p.rapidapi.com")
 * 	.build()
 *
 * val response = client.newCall(request).execute()
 */
class SneakersApiImp(private val httpClient: HttpClient) : SneakersApi {
    override suspend fun getSneakerList(): List<Sneaker> {
        return httpClient
            .get("https://sneaker-database-stockx.p.rapidapi.com/getproducts") {
                header("X-RapidAPI-Key", "c6aed5dadcmshed9d8b5d495c307p185849jsn971e13b2bcc8")
                header("X-RapidAPI-Host", "sneaker-database-stockx.p.rapidapi.com")
                parameter("limit", "20")
                parameter("keywords", "jordan")
            }
            .body<List<Sneaker>>()
    }
}