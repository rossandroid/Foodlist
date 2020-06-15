package com.rossellamorgante.foodlist.dependencyinj

import com.rossellamorgante.foodlist.model.MenuService
import com.rossellamorgante.foodlist.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: MenuService)

    fun inject(viewModel: ListViewModel)


}