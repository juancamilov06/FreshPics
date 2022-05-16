package com.juanvilla.freshpic.di

import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSource
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSourceImpl
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSource
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSourceImpl
import com.juanvilla.freshpic.data.source.remote.search.SearchRemoteDataSource
import com.juanvilla.freshpic.data.source.remote.search.SearchRemoteDataSourceImpl
import com.juanvilla.freshpic.data.source.remote.trending.TrendingRemoteDataSource
import com.juanvilla.freshpic.data.source.remote.trending.TrendingRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindTrendingRemoteDataSource(
        trendingRemoteDataSourceImpl: TrendingRemoteDataSourceImpl
    ): TrendingRemoteDataSource

    @Binds
    @Singleton
    fun bindSearchRemoteDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource

    @Binds
    @Singleton
    fun bindFavoriteLocalDataSource(
        favoritesLocalDataSourceImpl: FavoritesLocalDataSourceImpl
    ): FavoritesLocalDataSource

    @Binds
    @Singleton
    fun bindAgeControlLocalDataSource(
        ageControlLocalDataSourceImpl: AgeControlLocalDataSourceImpl
    ): AgeControlLocalDataSource
}