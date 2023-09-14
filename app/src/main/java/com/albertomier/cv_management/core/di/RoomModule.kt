package com.albertomier.cv_management.core.di

import android.content.Context
import androidx.room.Room
import com.albertomier.cv_management.core.database.AppDatabase
import com.albertomier.cv_management.core.utils.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCompanyDao(db: AppDatabase) = db.getCompanyDao()
}