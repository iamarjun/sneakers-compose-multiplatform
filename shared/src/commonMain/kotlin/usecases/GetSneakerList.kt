package usecases

import model.Sneaker
import network.SneakersApiImp

class GetSneakers(
    private val sneakersApi: SneakersApiImp
) {
    suspend operator fun invoke(): Result<List<Sneaker>> {
        return try {
            val list = sneakersApi.getSneakerList()
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}