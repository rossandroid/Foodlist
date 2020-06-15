package com.rossellamorgante.foodlist.model

import com.rossellamorgante.foodlist.dependencyinj.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class MenuService {

    @Inject
    lateinit var api: MenuApi
    init{
        DaggerApiComponent.create().inject(this)
    }

    fun getMenu(): Single<List<Food>> {
        return api.getMenu()
    }
}