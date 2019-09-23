package com.pschsch.vkgarbagecleaner.ui.main.model

import com.pschsch.vkgarbagecleaner.model.Item

data class MainModel(
    val videos : List<Item>,
    val connectionError : Boolean
)