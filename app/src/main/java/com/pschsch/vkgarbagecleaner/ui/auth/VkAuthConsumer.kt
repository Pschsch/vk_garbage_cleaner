package com.pschsch.vkgarbagecleaner.ui.auth

import com.vk.api.sdk.auth.VKAccessToken


interface VkAuthConsumer {
    fun onVkSuccess(token : VKAccessToken)
    fun onVkError(error : Int)
}