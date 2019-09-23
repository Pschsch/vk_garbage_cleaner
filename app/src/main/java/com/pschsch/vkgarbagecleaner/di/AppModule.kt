package com.pschsch.vkgarbagecleaner.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.pschsch.vkgarbagecleaner.utils.ResourcesManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val c : Context) {

    @Provides
    @Singleton
    fun provideSharedPreferenceManager(): SharedPreferenceManager {
        return SharedPreferenceManager(c)
    }

    @Provides
    @Singleton
    fun provideResManager() : ResourcesManager {
        return ResourcesManager(c)
    }

    @Provides
    @Singleton
    fun provideHandler() : Handler {
        return Handler(Looper.getMainLooper())
    }
}