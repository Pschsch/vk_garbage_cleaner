package com.pschsch.vkgarbagecleaner

import android.app.Application
import com.pschsch.vkgarbagecleaner.di.*
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class VkGarbageCleanerApplication : Application(){

    lateinit var appComponent : AppComponent
    private var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        VK.initialize(this)
    }

    fun getMainComponent() : MainComponent {
        if(mainComponent == null) {
            mainComponent = appComponent.plusMainComponent(MainModule())

        }
        return mainComponent!!
    }

    fun closeMainComponent() {
        mainComponent = null
    }

    companion object {
        private lateinit var app : VkGarbageCleanerApplication

        fun provideApp(): VkGarbageCleanerApplication {
            return app
        }
    }
}