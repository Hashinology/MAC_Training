package com.example.jasongomez.fragmentcommunicationkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), RecyclerViewFragment.OnPersonSelectedListener {

    private lateinit var recyclerViewFragment: RecyclerViewFragment
    private lateinit var itemFragment: ItemFragment

    companion object {

        private const val RECYCLER_VIEW_FRAGMENT_TAG = "rvFragment"
        private const val ITEM_FRAGMENT_TAG = "itemFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewFragment = RecyclerViewFragment()
        itemFragment = ItemFragment()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.rvFragContainer, recyclerViewFragment, RECYCLER_VIEW_FRAGMENT_TAG)
                .add(R.id.itemFragContainer, itemFragment, ITEM_FRAGMENT_TAG)
                .commit()
    }


    override fun onPersonSelected(person: Person) {
        val fragment = supportFragmentManager.findFragmentByTag(ITEM_FRAGMENT_TAG) as ItemFragment
        fragment.personSelected(person)
    }
}
