package com.pschsch.vkgarbagecleaner.utils

import android.view.View

inline fun <reified T> requireNonNull(obj: T?): T {
    return obj
        ?: throw NullPointerException("Object with type ${T::class.simpleName} must not be null")
}

fun <T> T?.orReturn(value: T): T {
    return this ?: value
}

fun View?.setVisible(visible: Boolean) {
    this?.visibility = if (visible) View.VISIBLE else View.GONE
}