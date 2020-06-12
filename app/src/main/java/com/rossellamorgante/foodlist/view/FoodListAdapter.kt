package com.rossellamorgante.foodlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rossellamorgante.foodlist.R
import com.rossellamorgante.foodlist.model.Food
import com.rossellamorgante.foodlist.util.getProgressDrawable
import com.rossellamorgante.foodlist.util.loadImage
import kotlinx.android.synthetic.main.item_food.view.*

class FoodListAdapter(var menu: ArrayList<Food>): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val imageView = view.imageView
        private val foodType = view.type
        private val foodName = view.name
        private val progressDrawable = getProgressDrawable(view.context)
        fun bind(food: Food){
            foodName.text = food.foodName
            foodType.text = food.type
            imageView.loadImage(food.picture, progressDrawable)
        }
    }
    fun updateMenu(newFood: List<Food>){
        menu.clear()
        menu.addAll(newFood)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false))

    override fun getItemCount() = menu.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(menu[position])
    }
}