package com.universe.tvmaze.di

import com.universe.tvmaze.BuildConfig
import com.universe.tvmaze.data.network.TvMazeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val customClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
        /*Enable interceptor only in debug mode*/

        if(BuildConfig.DEBUG)
            customClient.addInterceptor(interceptor)
        val clientBuilder = customClient.build()

        return Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder)
            .build()
    }
    @Singleton
    @Provides
    fun provideTvMazeApi(retrofit: Retrofit): TvMazeApiClient{
        return retrofit.create(TvMazeApiClient::class.java)
    }
}