package com.juanvilla.freshpic.di

import com.juanvilla.freshpic.domain.usecase.agecontrol.AgeControlUseCase
import com.juanvilla.freshpic.domain.usecase.agecontrol.AgeControlUseCaseImpl
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCaseImpl
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCase
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCaseImpl
import com.juanvilla.freshpic.domain.usecase.trending.TrendingUseCase
import com.juanvilla.freshpic.domain.usecase.trending.TrendingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindFavoritesUseCase(
        favoriteUseCaseImpl: FavoriteUseCaseImpl
    ): FavoriteUseCase

    @Binds
    @Singleton
    fun bindSearchUseCase(
        searchUseCaseImpl: SearchUseCaseImpl
    ): SearchUseCase

    @Binds
    @Singleton
    fun bindTrendingUseCase(
        trendingUseCaseImpl: TrendingUseCaseImpl
    ): TrendingUseCase

    @Binds
    @Singleton
    fun bindAgeControlUseCase(
        ageControlUseCaseImpl: AgeControlUseCaseImpl
    ): AgeControlUseCase
}