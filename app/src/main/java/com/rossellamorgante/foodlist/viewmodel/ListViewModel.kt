package com.rossellamorgante.foodlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rossellamorgante.foodlist.dependencyinj.DaggerApiComponent
import com.rossellamorgante.foodlist.model.Food
import com.rossellamorgante.foodlist.model.MenuService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {
    @Inject
    lateinit var foodService: MenuService

    private val disposable =  CompositeDisposable()

    val menu = MutableLiveData<List<Food>>()
    val foodLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init{
        DaggerApiComponent.create().inject(this)
    }
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