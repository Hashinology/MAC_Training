package com.example.jasongomez.recyclerviewkotlin

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView

class FoodListAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var lastPosition = -1

    companion object {
        private const val TAG = "FoodListAdapter"
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvDishName: TextView = itemView.findViewById(R.id.tvFoodName)
        internal var tvCalories: TextView = itemView.findViewById(R.id.tvFoodCalories)
        internal var tvPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        internal var tvRating: TextView = itemView.findViewById(R.id.tvFoodRating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.foodlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //adding animation to the itemviews
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            animation.setInterpolator(context, android.R.interpolator.bounce)
            holder.itemView.startAnimation(animation)
            lastPosition = position
        }

        Log.d(TAG, "onBindViewHolder: $position")
        val food = foodList[position]
        holder.apply {
            tvDishName.text = food.dishName
            tvCalories.text = food.calories.toString()
            tvRating.text = food.rating.toString()
            tvPrice.text = food.price.toString()
            itemView.setOnClickListener {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("food", food)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = foodList.size
}
