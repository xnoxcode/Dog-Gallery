package epm.xnox.doggallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epm.xnox.doggallery.data.network.ApiInterface
import epm.xnox.doggallery.data.repository.DogRepositoryImpl
import epm.xnox.doggallery.domain.repository.DogRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun getDogRepository(
        apiInterface: ApiInterface
    ) : DogRepository = DogRepositoryImpl(apiInterface)
}