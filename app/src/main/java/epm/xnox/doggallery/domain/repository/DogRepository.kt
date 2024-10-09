package epm.xnox.doggallery.domain.repository

import epm.xnox.doggallery.common.Result
import epm.xnox.doggallery.domain.model.Dogs
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogsByRaza(raza: String): Flow<Result<Dogs>>
    suspend fun getRandomDogs(count: Int): Flow<Result<Dogs>>
}