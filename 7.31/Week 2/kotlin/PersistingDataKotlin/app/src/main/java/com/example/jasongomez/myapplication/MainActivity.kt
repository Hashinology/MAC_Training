package com.example.jasongomez.myapplication

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editText1: EditText
    lateinit var editText2: EditText

    companion object {
        private val MY_PREF_FILE = "mypref_file"
        private val TAG = "Lifecycle_Main"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.etVal1)
        editText2 = findViewById(R.id.etVal2)

        Log.d(TAG, "onCreate: ")

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.d(TAG, "onConfigurationChanged: ")


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show()

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show()

    }


    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart: ")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause: ")

    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "onStop: ")

    }


    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: ")
    }

    fun saveData(view: View) {

        val sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("value1", editText1.text.toString())
        editor.putString("value2", editText2.text.toString())
        editor.commit()

        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)


    }

    fun getData(view: View) {

        val sharedPreferencess = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE)
        Log.d(TAG, "getData: " + sharedPreferencess.getString("value1", "default")!!)

    }
}

