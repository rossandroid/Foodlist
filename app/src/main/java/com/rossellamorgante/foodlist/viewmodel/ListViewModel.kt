package com.rossellamorgante.foodlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rossellamorgante.foodlist.model.Food
import com.rossellamorgante.foodlist.model.MenuService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    private val foodService = MenuService()
    private val disposable = CompositeDisposable()
    val menu = MutableLiveData<List<Food>>()
    val foodLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchMenu()
    }
    private fun fetchMenu(){
        loading.value = true
        disposable.add(
            foodService.getMenu()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(value: List<Food>) {
                        menu.value = value
                        foodLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        foodLoadError.value = true
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}