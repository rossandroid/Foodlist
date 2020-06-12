package com.rossellamorgante.foodlist.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MenuService {
    private val BASE_URL = "https://www.cookingidea.eu"
    private val api: MenuApi
    init{
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }

    fun getMenu(): Single<List<Food>> {
        return api.getMenu()
    }
}