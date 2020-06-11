package com.rossellamorgante.foodlist.model

import io.reactivex.Single
import retrofit2.http.GET

interface FoodsApi {
    @GET("RossApp/foods/foodlist.json")
    fun getFoods(): Single<List<Food>>

}