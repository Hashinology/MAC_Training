package com.example.jasongomez.layoutandviewkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var etNumber1: EditText
    lateinit var etNumber2: EditText
    lateinit var etPersonName: EditText
    lateinit var etPersonGender: EditText
    lateinit var btnName: Button
    lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding views
        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)
        btnName = findViewById(R.id.btnDoMagic)
        tvName = findViewById(R.id.tvName)
        etPersonName = findViewById(R.id.etPersonName)
        etPersonGender = findViewById(R.id.etPersonGender)

        //setting on click listener of the button
        btnName.setOnClickListener {

            val etNum1 = etNumber1.text.toString().toInt()
            val etNum2 = etNumber2.text.toString().toInt()
            tvName.text = (etNum1 + etNum2).toString()
        }

        Log.d(TAG, "onCreate: ")
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
        Log.d(TAG, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    fun goToSecond(view: View) {
        val value = etNumber1.text.toString()

        val intent = Intent(this, SecondActivity::class.java)
        intent.action = "sendingValue"
        intent.putExtra(getString(R.string.KEY_VALUE1), value)
        startActivity(intent)
    }

    fun passPersonToSecond(view: View) {
        val personName = etPersonName.text.toString()
        val personGender = etPersonGender.text.toString()

        val person = Person(personName, personGender)
        val intent = Intent(this, SecondActivity::class.java)
        intent.action = "sendingPerson"
        intent.putExtra("person", person)
        startActivity(intent)
    }
}
