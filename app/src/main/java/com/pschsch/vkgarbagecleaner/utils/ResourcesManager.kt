package com.pschsch.vkgarbagecleaner.utils

import android.content.Context
import androidx.annotation.StringRes

class ResourcesManager(private val c: Context) {
    fun getString(@StringRes resId: Int): String {
        return c.getString(resId)
    }
}