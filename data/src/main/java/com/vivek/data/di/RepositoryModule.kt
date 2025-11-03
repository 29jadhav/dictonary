package com.vivek.data.di

import com.vivek.data.repository.DictionaryRepositoryImpl
import com.vivek.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDictionaryRepository(dictionaryRepositoryImpl: DictionaryRepositoryImpl): DictionaryRepository
}