package com.rossellamorgante.foodlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rossellamorgante.foodlist.R
import com.rossellamorgante.foodlist.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val foodsAdapter = FoodListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        foodlist.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = foodsAdapter
        }

        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.foods.observe(this, Observer{foods ->
            foods?.let{foodsAdapter.updateFoods(it)}
        })
        viewModel.foodLoadError.observe(this, Observer{ isError ->
            isError?.let{list_error.visibility = if(it) View.VISIBLE else View.GONE}
        })
        viewModel.loading.observe(this, Observer{ isLoading ->
            isLoading?.let{
                loading.visibility = if(it) View.VISIBLE else View.GONE
            if(it){
                list_error.visibility = View.GONE
                foodlist.visibility = View.GONE
            }}
        })
    }
}
