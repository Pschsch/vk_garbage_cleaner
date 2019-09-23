package com.pschsch.vkgarbagecleaner.ui.auth

import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.VkGarbageCleanerApplication
import com.pschsch.vkgarbagecleaner.base.BaseViewModel
import com.pschsch.vkgarbagecleaner.utils.ResourcesManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager.Companion.TOKEN
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager.Companion.USER_ID
import com.pschsch.vkgarbagecleaner.utils.orReturn
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import javax.inject.Inject

class AuthViewModel : BaseViewModel() {

    @Inject
    lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var resManager : ResourcesManager


    init {
        VkGarbageCleanerApplication.provideApp().appComponent.inject(this)
    }

    fun onVkSuccess(token: VKAccessToken) {
        preferenceManager.saveData(TOKEN, token.accessToken)
        preferenceManager.saveData(USER_ID, token.userId.orReturn(0))
        navigateTo(R.id.mainFragment)
    }

    fun onVkError(error: Int) {
        when(error){
            VKAuthCallback.AUTH_CANCELED -> {
                errorMessageLiveData.postValue(resManager.getString(R.string.authorize_error))
            }
            VKAuthCallback.UNKNOWN_ERROR -> {
                errorMessageLiveData.postValue(resManager.getString(R.string.unexpected_error))
            }
        }

    }
}