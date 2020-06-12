package com.rossellamorgante.foodlist.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("foodName")
    val foodName: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("foodpng")
    val picture: String?
)
