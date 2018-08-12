package com.example.jasongomez.recyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var foodList: MutableList<Food> = ArrayList()
    private lateinit var rvFoodList: RecyclerView
    private lateinit var foodListAdapter: FoodListAdapter
    private lateinit var rvItemAnimator: RecyclerView.ItemAnimator
    lateinit var linearLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvFoodList = findViewById(R.id.rvFoodList)
        linearLayoutManager = LinearLayoutManager(this)
        rvItemAnimator = DefaultItemAnimator()

        //initialize the adapter
        foodListAdapter = FoodListAdapter(foodList)
        initFoodList()
        foodListAdapter.notifyDataSetChanged()

        rvFoodList.apply {
            layoutManager = linearLayoutManager
            itemAnimator = rvItemAnimator
            adapter = foodListAdapter

        }

    }

    private fun initFoodList() {

        foodList.add(Food("Burger", 12, 300, 4.5))
        foodList.add(Food("Pizza", 34, 340, 4.9))
        foodList.add(Food("Soup", 14, 500, 4.1))
        foodList.add(Food("Fried rice", 15, 600, 2.5))
    }
}
