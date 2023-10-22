package usecases

import domain.SneakersRepository
import model.Sneaker

class GetSneakerList(private val sneakersRepository: SneakersRepository) {
    suspend operator fun invoke(): Result<List<Sneaker>> {
        return sneakersRepository.getSneakerList()
    }
}