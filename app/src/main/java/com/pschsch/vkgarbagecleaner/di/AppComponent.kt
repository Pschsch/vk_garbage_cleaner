package com.pschsch.vkgarbagecleaner.di

import com.pschsch.vkgarbagecleaner.ui.MainActivity
import com.pschsch.vkgarbagecleaner.ui.auth.AuthViewModel
import com.pschsch.vkgarbagecleaner.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun plusMainComponent(m : MainModule) : MainComponent
    fun inject(act : MainActivity)
    fun inject(vm : AuthViewModel)
    fun inject(vm : SplashViewModel)
}