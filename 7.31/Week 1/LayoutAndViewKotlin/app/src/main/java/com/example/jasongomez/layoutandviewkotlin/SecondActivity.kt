package com.example.jasongomez.layoutandviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    companion object {
        val TAG = "Second"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        when (intent.action) {
            "sendingValue" -> Log.d(TAG, "onCreate: " + intent.getStringExtra(getString(R.string.KEY_VALUE1)))
            "sendingPerson" -> {
                val person = intent.getSerializableExtra("person") as Person
                Log.d(TAG, "onCreate: " + person.toString())
            }
        }
    }
}
