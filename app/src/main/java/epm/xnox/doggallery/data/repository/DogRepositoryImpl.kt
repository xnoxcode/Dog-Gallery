package epm.xnox.doggallery.data.repository

import epm.xnox.doggallery.common.Result
import epm.xnox.doggallery.data.network.ApiInterface
import epm.xnox.doggallery.domain.model.Dogs
import epm.xnox.doggallery.domain.model.toDomain
import epm.xnox.doggallery.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(private val api: ApiInterface) : DogRepository {

    override suspend fun getDogsByRaza(raza: String): Flow<Result<Dogs>> = flow {
        emit(Result.Loading())

        val result: Dogs = api.getDogsByRaza(raza).toDomain()

        emit(Result.Success(result))
    }
        .flowOn(Dispatchers.IO)
        .catch {
            emit(Result.Error(it.message ?: "Error desconocido"))
        }

    override suspend fun getRandomDogs(count: Int): Flow<Result<Dogs>> = flow {
        emit(Result.Loading())

        val result: Dogs = api.getRandomDogs(count).toDomain()

        emit(Result.Success(result))
    }
        .flowOn(Dispatchers.IO)
        .catch {
            emit(Result.Error(it.message ?: "Error desconocido"))
        }
}


