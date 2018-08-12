package com.example.jasongomez.recyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Second"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val food = intent.getSerializableExtra("food") as Food
        Log.d(TAG, "onCreate: " + food.toString())
    }

}
