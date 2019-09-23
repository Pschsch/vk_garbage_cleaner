package com.pschsch.vkgarbagecleaner.di

import com.pschsch.vkgarbagecleaner.model.network.VKClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {
    @Provides
    @MainScope
    fun provideVkClient(): VKClient {
        return Retrofit.Builder()
            .baseUrl("https://api.vk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(VKClient::class.java)
    }
}