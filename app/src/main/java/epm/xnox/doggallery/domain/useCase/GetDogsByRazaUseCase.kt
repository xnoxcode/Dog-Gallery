package epm.xnox.doggallery.domain.useCase

import epm.xnox.doggallery.domain.repository.DogRepository
import javax.inject.Inject

class GetDogsByRazaUseCase @Inject constructor(private val repository: DogRepository) {
    suspend operator fun invoke(raza: String) = repository.getDogsByRaza(raza)
}