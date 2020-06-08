package com.rossellamorgante.foodlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rossellamorgante.foodlist.model.Food

class ListViewModel: ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val foodLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFoods()
    }
    private fun fetchFoods(){
        val mockData = listOf(Food("Pizza"),
            Food("Spaghetti"),
            Food("Burger"),
            Food("Tiramisu"),
            Food("Fish"),
            Food("Chips"),
            Food("Cake"),
            Food("Muffin")
        )
        foodLoadError.value = false
        loading.value = false
        foods.value = mockData
    }


}