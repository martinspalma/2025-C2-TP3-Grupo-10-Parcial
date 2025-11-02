package com.ort.parcial.c2.tp3.grupo10.di

import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.AuthService
import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.implementation.AuthImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthService(
        authImpl: AuthImpl
    ): AuthService
}
