package com.juanvilla.freshpic.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.juanvilla.freshpic.data.source.local.GifDatabase
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlSerializer
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.local.dao.UserDao
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAgeControlDataStore(
        @ApplicationContext appContext: Context,
        @Named("IoDispatcher") ioDispatcher: CoroutineDispatcher
    ): DataStore<ProtoAgeControlPreferences> =
        DataStoreFactory.create(
            serializer = AgeControlSerializer,
            scope = CoroutineScope(ioDispatcher),
            produceFile = { appContext.dataStoreFile("freshgifsprefs.pb") }
        )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GifDatabase =
        Room.databaseBuilder(
            context,
            GifDatabase::class.java,
            "gif_database"
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(
        gifDatabase: GifDatabase
    ): UserDao = gifDatabase.getUserDao()

    @Provides
    @Singleton
    fun provideFavoritesDao(
        gifDatabase: GifDatabase
    ): FavoritesDao = gifDatabase.getFavoritesDao()
}