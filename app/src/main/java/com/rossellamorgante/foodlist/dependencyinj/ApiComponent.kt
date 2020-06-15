package com.rossellamorgante.foodlist.dependencyinj

import com.rossellamorgante.foodlist.model.MenuService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: MenuService)
}