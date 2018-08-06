package com.example.jasongomez.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


class SecondActivity : AppCompatActivity() {

    companion object {
        private val TAG = "Second"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val personList: List<Person>
        personList = intent.getParcelableArrayListExtra("person")

        Toast.makeText(this, personList.size, Toast.LENGTH_SHORT).show()

        //        Person person = getIntent().getParcelableExtra("person");
        //        Log.d(TAG, "onCreate: " + person.getName());
        //
        //
        //        Toast.makeText(this, person.getName(), Toast.LENGTH_SHORT).show();

    }
}
