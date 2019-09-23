package com.pschsch.vkgarbagecleaner.di

import com.pschsch.vkgarbagecleaner.ui.main.MainViewModel
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun inject(v : MainViewModel)
}