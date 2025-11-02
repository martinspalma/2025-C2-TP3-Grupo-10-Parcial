package com.ort.parcial.c2.tp3.grupo10.di

import com.ort.parcial.c2.tp3.grupo10.data.repository.FallbackTransactionsRepository
import com.ort.parcial.c2.tp3.grupo10.data.repository.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionsDataModule {

    @Binds
    @Singleton
    abstract fun bindTransactionsRepository(
        impl: FallbackTransactionsRepository
    ): TransactionsRepository
}
