package com.rossellamorgante.foodlist.dependencyinj

import com.rossellamorgante.foodlist.model.MenuApi
import com.rossellamorgante.foodlist.model.MenuService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://www.cookingidea.eu"

    @Provides
    fun provideMenuApi(): MenuApi{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }
    @Provides
    fun provideMenuService(): MenuService {
        return MenuService()
    }
}