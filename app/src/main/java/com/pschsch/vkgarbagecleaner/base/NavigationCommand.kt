package com.pschsch.vkgarbagecleaner.base

sealed class NavigationCommand {
    class To(val destinationId : Int) : NavigationCommand()
}