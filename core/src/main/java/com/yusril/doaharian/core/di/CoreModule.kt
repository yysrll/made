package com.yusril.doaharian.core.di

import androidx.room.Room
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.data.local.LocalDataSource
import com.yusril.doaharian.core.data.local.room.DoaDatabase
import com.yusril.doaharian.core.data.remote.RemoteDataSource
import com.yusril.doaharian.core.data.remote.network.ApiService
import com.yusril.doaharian.core.domain.repository.IDoaRepository
import com.yusril.doaharian.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<DoaDatabase>().doaDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DoaDatabase::class.java, "doaDB"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://islamic-api-indonesia.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IDoaRepository> { DoaRepository(get(), get(), get()) }
}