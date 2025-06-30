package com.devrachit.groww.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.devrachit.groww.data.remote.services.AlphaVantageApiService
import com.devrachit.groww.di.qualifiers.WithChucker
import com.devrachit.groww.di.qualifiers.WithoutChucker
import com.devrachit.groww.utility.constants.Constants.Companion.BASE_URL
import com.devrachit.groww.utility.constants.Constants.Companion.CACHE_SIZE
import com.devrachit.groww.utility.networkUtility.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @WithChucker
    fun provideOkHttpClientWithChucker(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()
    }

    //    @Provides
//    @Singleton
//    @WithoutChucker
//    fun provideOkHttpClientWithoutChucker(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .build()
//    }
    @Singleton
    @Provides
    @WithoutChucker
    fun providesOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .addInterceptor { chain ->
            var request = chain.request()

            request = if (NetworkManager(context).isConnected())
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()

            chain.proceed(request)
        }
        .build()


    @Provides
    @Singleton
    fun provideAlphaVantageApi(@WithoutChucker okhttpClient: OkHttpClient): AlphaVantageApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlphaVantageApiService::class.java)
    }


}