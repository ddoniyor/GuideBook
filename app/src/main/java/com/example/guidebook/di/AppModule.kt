package com.example.guidebook.di

import android.content.Context
import androidx.room.Room
import com.example.guidebook.common.Constants
import com.example.guidebook.data.local.db.AppDatabase
import com.example.guidebook.data.remote.api.GuideBookApi
import com.example.guidebook.data.repository.GuideBookRepositoryImpl
import com.example.guidebook.domain.repository.GuideBookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGuideBookApi(): GuideBookApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GuideBookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: GuideBookApi,db:AppDatabase): GuideBookRepository {
        return GuideBookRepositoryImpl(api, db)
    }
}