package ru.dubr.traineetestandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.dubr.traineetestandroid.data.repository.CoinRepositoryImpl
import ru.dubr.traineetestandroid.domain.repository.CoinRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsCoinRepository(
        impl: CoinRepositoryImpl
    ): CoinRepository
}