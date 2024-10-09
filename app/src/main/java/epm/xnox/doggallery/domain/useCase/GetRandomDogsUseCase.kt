package epm.xnox.doggallery.domain.useCase

import epm.xnox.doggallery.domain.repository.DogRepository
import javax.inject.Inject

class GetRandomDogsUseCase @Inject constructor(private val repository: DogRepository) {
    suspend operator fun invoke(count: Int) = repository.getRandomDogs(count)
}