package com.pschsch.vkgarbagecleaner.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.VkGarbageCleanerApplication
import com.pschsch.vkgarbagecleaner.base.BaseViewModel
import com.pschsch.vkgarbagecleaner.base.GlobalParams
import com.pschsch.vkgarbagecleaner.model.Item
import com.pschsch.vkgarbagecleaner.model.network.VKClient
import com.pschsch.vkgarbagecleaner.ui.main.model.MainModel
import com.pschsch.vkgarbagecleaner.ui.main.model.MainProgressModel
import com.pschsch.vkgarbagecleaner.utils.ResourcesManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager.Companion.TOKEN
import com.pschsch.vkgarbagecleaner.utils.SharedPreferenceManager.Companion.USER_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var vkClient: VKClient
    @Inject
    lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var resManager: ResourcesManager


    val progressLiveData = MutableLiveData<MainProgressModel>()
    val baseLiveData = MutableLiveData<MainModel>()
    val garbageCleanData = MutableLiveData<Int>()
    val rawData = mutableListOf<Item>()

    fun init() {
        VkGarbageCleanerApplication.provideApp().getMainComponent().inject(this)
        progressLiveData.postValue(MainProgressModel(progressVisibility = true))
        viewModelScope.launch {
            try {
                val videos = withContext(Dispatchers.IO) {
                    vkClient.getVideo(
                        preferenceManager.getInt(USER_ID),
                        preferenceManager.getString(TOKEN), GlobalParams.VK_API_VER
                    )
                }.response.items
                rawData.addAll(videos)
                progressLiveData.postValue(MainProgressModel(progressVisibility = false))
                baseLiveData.postValue(MainModel(videos, false))
            } catch (e: Exception) {
                e.printStackTrace()
                when (e) {
                    is ConnectException, is UnknownHostException -> {
                        baseLiveData.postValue(MainModel(listOf(), true))
                    }
                    else -> errorMessageLiveData.postValue(resManager.getString(R.string.error_of_get_response))
                }
                progressLiveData.postValue(MainProgressModel(progressVisibility = false))
            }

        }
    }

    fun startGarbageClean(){
        var removed = 0
        rawData.forEach {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    Thread.sleep(1000)
                    vkClient.deleteVideo(it.id,
                        it.ownerId,
                        preferenceManager.getInt(USER_ID),
                        preferenceManager.getString(TOKEN),
                        GlobalParams.VK_API_VER
                    )
                }.response
                if(response == 1) {
                    removed++
                    garbageCleanData.postValue(removed)
                }
            }
        }
        if(removed != rawData.size){
            garbageCleanData.postValue(rawData.size)
        }
    }

    override fun onCleared() {
        VkGarbageCleanerApplication.provideApp().closeMainComponent()
        super.onCleared()
    }
}