package com.pschsch.vkgarbagecleaner.ui.splash

import android.util.Log
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.VkGarbageCleanerApplication
import com.pschsch.vkgarbagecleaner.base.BaseViewModel
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager.Companion.TOKEN
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {
    @Inject
    lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var handler: android.os.Handler

    init {
        VkGarbageCleanerApplication.provideApp().appComponent.inject(this)
    }

    fun init() {
        val token = preferenceManager.getString(TOKEN)
        handler.postDelayed({
            if(token.isEmpty()){
                navigateTo(R.id.authFragment)
            } else {
                navigateTo(R.id.mainFragment)
            }
        },400)
    }
}