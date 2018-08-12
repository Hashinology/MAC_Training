package com.example.jasongomez.handlingfragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), BlueFragment.OnFragmentInteractionListener {

    private lateinit var tvFromBlue: TextView

    companion object {

        private const val RED_FRAGMENT_TAG = "RedFragment"
        private const val YELLOW_FRAGMENT_TAG = "YellowFragment"
        private const val BLUE_FRAGMENT_TAG = "BlueFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvFromBlue = findViewById(R.id.tvFromBlue)

    }

    override fun onFragmentInteraction(s: String) {
        tvFromBlue.text = s
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
    }

    fun addFragments(view: View) {
        when (view.id) {

            R.id.btnAddRed -> {
                val redFragment = RedFragment()
                supportFragmentManager.beginTransaction()
                        .add(R.id.flFrag1, redFragment, RED_FRAGMENT_TAG)
                        .addToBackStack(RED_FRAGMENT_TAG)
                        .commit()
            }

            R.id.btnAddYellow -> {
                val yellowFragment = YellowFragment.newInstance("John", "Doe")
                supportFragmentManager.beginTransaction()
                        .add(R.id.flFrag1, yellowFragment, YELLOW_FRAGMENT_TAG)
                        .addToBackStack(YELLOW_FRAGMENT_TAG)
                        .commit()
            }

            R.id.btnAddBlue -> {
                val blueFragment = BlueFragment.newInstance("John", "Doe")
                supportFragmentManager.beginTransaction()
                        .add(R.id.flFrag1, blueFragment, BLUE_FRAGMENT_TAG)
                        .addToBackStack(BLUE_FRAGMENT_TAG)
                        .commit()
            }
        }

    }

    fun removeFragments(view: View) {
        when (view.id) {

            R.id.btnRemoveRed -> {

                val fragmentRed = supportFragmentManager.findFragmentByTag(RED_FRAGMENT_TAG)
                supportFragmentManager.beginTransaction()
                        .remove(fragmentRed!!)
                        .commit()
            }

            R.id.btnRemoveYellow -> {

                val fragmentYellow = supportFragmentManager.findFragmentByTag(YELLOW_FRAGMENT_TAG)
                supportFragmentManager.beginTransaction()
                        .remove(fragmentYellow!!)
                        .commit()
            }

            R.id.btnRemoveBlue -> {

                val fragmentBlue = supportFragmentManager.findFragmentByTag(BLUE_FRAGMENT_TAG)
                supportFragmentManager.beginTransaction()
                        .remove(fragmentBlue!!)
                        .commit()
            }


            R.id.btnRemoveAll ->

                while (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStackImmediate()
                }
        }


    }
}

