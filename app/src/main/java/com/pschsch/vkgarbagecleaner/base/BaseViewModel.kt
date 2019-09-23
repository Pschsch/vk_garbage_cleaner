package com.pschsch.vkgarbagecleaner.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val navigationLiveData = MutableLiveData<NavigationCommand>()

    val errorMessageLiveData = MutableLiveData<String>()

    protected fun navigateTo(destinationId: Int) {
        navigationLiveData.postValue(NavigationCommand.To(destinationId))
    }
}