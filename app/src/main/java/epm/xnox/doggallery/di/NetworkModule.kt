package epm.xnox.doggallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import epm.xnox.doggallery.data.network.BASE_URL
import epm.xnox.doggallery.data.network.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getBaseUrl(): String = BASE_URL

    @Singleton
    @Provides
    fun getRetrofitClient(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun getApiCLient(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}