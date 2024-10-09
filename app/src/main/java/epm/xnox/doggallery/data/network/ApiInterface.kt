package epm.xnox.doggallery.data.network

import epm.xnox.doggallery.domain.model.DogResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(DOGS_BY_RAZA)
    suspend fun getDogsByRaza(@Path("raza") raza: String): DogResponse
    @GET(RANDOM_DOGS)
    suspend fun getRandomDogs(@Path("count") count: Int): DogResponse
}