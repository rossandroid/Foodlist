package com.rossellamorgante.foodlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rossellamorgante.foodlist.R
import com.rossellamorgante.foodlist.model.Food
import kotlinx.android.synthetic.main.item_food.view.*

class FoodListAdapter(var foods: ArrayList<Food>): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view){
       private val foodName = view.name
        fun bind(food: Food){
            foodName.text = food.foodName
        }
    }
    fun updateFoods(newFood: List<Food>){
        foods.clear()
        foods.addAll(newFood)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false))

    override fun getItemCount() = foods.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position])
    }
}