package com.example.jasongomez.myapplication

import android.content.Intent
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.etname)
        textView = findViewById(R.id.tvName)


    }

    fun changeText(view: View) {

        val data = editText.text.toString()
        textView.text = data


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val data = textView.text.toString()
        outState.putString("data", data)

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        textView.text = savedInstanceState.getString("data")


    }

    fun doSomething(view: View) {


        when (view.id) {

            R.id.btnChangeText -> {
            }

            R.id.btnGoToSecond -> {


                val personList = ArrayList<Person>()

                personList.add(Person("sdf", "sdf"))
                personList.add(Person("sdfd", "Sdf"))

                val intent = Intent(this, SecondActivity::class.java)
                intent.putParcelableArrayListExtra("person", personList as ArrayList<out Parcelable>)
                startActivity(intent)
            }

            R.id.btnShareData -> {

                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is a message")
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
        }


    }
}
