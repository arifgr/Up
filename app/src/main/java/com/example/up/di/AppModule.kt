package com.example.up.di

import com.example.up.common.Constants.BASE_URL
import com.example.up.data.remote.CardApi
import com.example.up.data.repository.CardRepositoryImpl
import com.example.up.domain.repository.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCardApi(): CardApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(CardApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCardRepository(api: CardApi): CardRepository {
        return CardRepositoryImpl(api)
    }
}