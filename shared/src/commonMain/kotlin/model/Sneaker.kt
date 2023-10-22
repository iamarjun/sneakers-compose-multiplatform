package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sneaker(
    @SerialName("brand")
    val brand: String,
    @SerialName("colorway")
    val colorway: String,
    @SerialName("description")
    val description: String,
    @SerialName("goatProductId")
    val goatProductId: Int,
    @SerialName("_id")
    val id: String,
    @SerialName("make")
    val make: String,
    @SerialName("releaseDate")
    val releaseDate: String,
    @SerialName("retailPrice")
    val retailPrice: Int,
    @SerialName("shoeName")
    val shoeName: String,
    @SerialName("silhoutte")
    val silhoutte: String,
    @SerialName("styleID")
    val styleID: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("urlKey")
    val urlKey: String
)