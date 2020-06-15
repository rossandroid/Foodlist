package com.rossellamorgante.foodlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rossellamorgante.foodlist.model.Food
import com.rossellamorgante.foodlist.model.MenuService
import com.rossellamorgante.foodlist.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var menuService: MenuService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Food>>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun getMenuSuccess(){
        val food = Food("foodName", "type", "foodpng")
        val menuList = arrayListOf(food)

        testSingle = Single.just(menuList)

        `when`(menuService.getMenu()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.menu.value?.size)
        Assert.assertEquals(false, listViewModel.foodLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)

    }

    @Test
    fun getMenuFail(){
        testSingle = Single.error(Throwable())

        `when`(menuService.getMenu()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(true, listViewModel.foodLoadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)

    }
    @Before
    fun setUpRxSchedulers(){
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor {it.run() }, true)
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate}
    }
}