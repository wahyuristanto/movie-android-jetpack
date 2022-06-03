package skycode.dicoding.jetpack.mvvm.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skycode.dicoding.jetpack.mvvm.BuildConfig
import skycode.dicoding.jetpack.mvvm.data.remote.ApiService
import skycode.dicoding.jetpack.mvvm.utils.UtilConst
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.PATH_API_KEY
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.PATH_DEFAULT_LANG

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        //return BuildConfig.BASE_URL
        return "https://api.themoviedb.org/3/"
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    fun providesOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter(PATH_API_KEY, UtilConst.API_KEY)
                    .addQueryParameter(PATH_DEFAULT_LANG, UtilConst.DEFAULT_LANG)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    fun providesRetrofit(baseUrl: String, converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}