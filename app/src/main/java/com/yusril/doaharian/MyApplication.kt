package com.yusril.doaharian

import android.app.Application
import com.yusril.doaharian.core.di.databaseModule
import com.yusril.doaharian.core.di.networkModule
import com.yusril.doaharian.core.di.repositoryModule
import com.yusril.doaharian.di.useCaseModule
import com.yusril.doaharian.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}