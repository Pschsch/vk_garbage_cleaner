package com.pschsch.vkgarbagecleaner.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.VkGarbageCleanerApplication
import com.pschsch.vkgarbagecleaner.ui.auth.VkAuthConsumer
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VkGarbageCleanerApplication.provideApp().appComponent.inject(this)
        VK.addTokenExpiredHandler(tokenTracker)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VK.onActivityResult(
                requestCode,
                resultCode,
                data, callback
            )
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private val callback = object : VKAuthCallback {
        override fun onLogin(token: VKAccessToken) {
            supportFragmentManager.fragments.forEach {
                Log.e(TAG, it.toString())
                if (it is NavHostFragment) {
                    it.childFragmentManager.fragments.forEach { child ->
                        if (child is VkAuthConsumer) {
                            child.onVkSuccess(token)
                        }
                    }
                }
            }
        }

        override fun onLoginFailed(errorCode: Int) {
            supportFragmentManager.fragments.forEach {
                if (it is NavHostFragment) {
                    it.childFragmentManager.fragments.forEach { child ->
                        if (child is VkAuthConsumer) {
                            child.onVkError(errorCode)
                        }
                    }
                }
            }
        }
    }

    private val tokenTracker = object : VKTokenExpiredHandler {
        override fun onTokenExpired() {
            preferenceManager.clearToken()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
