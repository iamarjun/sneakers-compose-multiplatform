package network

import model.Sneaker

interface SneakersApi {
    suspend fun getSneakerList(): List<Sneaker>
}