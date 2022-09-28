package ru.dubr.traineetestandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dubr.traineetestandroid.data.network.CoinApi
import ru.dubr.traineetestandroid.utils.Const
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



    @Singleton
    @Provides
    fun provideRetrofit(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }
}