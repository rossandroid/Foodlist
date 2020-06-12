package com.rossellamorgante.foodlist.model

import io.reactivex.Single
import retrofit2.http.GET

interface MenuApi {
    @GET("RossApp/menu/foodlist.json")
    fun getMenu(): Single<List<Food>>

}