package com.juanvilla.freshpic.di

import com.juanvilla.freshpic.data.mappers.DbGifMapper
import com.juanvilla.freshpic.data.mappers.GifWrapperMapper
import com.juanvilla.freshpic.data.mappers.ProtoAgeMapper
import com.juanvilla.freshpic.data.repository.AgeControlRepositoryImpl
import com.juanvilla.freshpic.data.repository.FavoriteRepositoryImpl
import com.juanvilla.freshpic.data.repository.SearchRepositoryImpl
import com.juanvilla.freshpic.data.repository.TrendingRepositoryImpl
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSource
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSource
import com.juanvilla.freshpic.data.source.remote.search.SearchRemoteDataSource
import com.juanvilla.freshpic.data.source.remote.trending.TrendingRemoteDataSource
import com.juanvilla.freshpic.domain.repository.AgeControlRepository
import com.juanvilla.freshpic.domain.repository.FavoriteRepository
import com.juanvilla.freshpic.domain.repository.SearchRepository
import com.juanvilla.freshpic.domain.repository.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAgeControlRepository(
        ageControlLocalDataSource: AgeControlLocalDataSource
    ): AgeControlRepository = AgeControlRepositoryImpl(
        ageControlLocalDataSource,
        ProtoAgeMapper
    )

    @Provides
    @Singleton
    fun provideTrendingRepository(
        trendingRemoteDataSource: TrendingRemoteDataSource
    ): TrendingRepository = TrendingRepositoryImpl(
        trendingRemoteDataSource,
        GifWrapperMapper
    )

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchRemoteDataSource: SearchRemoteDataSource
    ): SearchRepository = SearchRepositoryImpl(
        searchRemoteDataSource,
        GifWrapperMapper
    )

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        favoritesLocalDataSource: FavoritesLocalDataSource
    ): FavoriteRepository = FavoriteRepositoryImpl(
        favoritesLocalDataSource,
        DbGifMapper
    )
}