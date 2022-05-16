package com.juanvilla.freshpic.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.remote.ApiKeyInterceptor
import com.juanvilla.freshpic.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideGiphyApi(
        retrofit: Retrofit
    ): GiphyAPI = retrofit.create(GiphyAPI::class.java)
}