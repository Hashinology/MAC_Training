package com.example.jasongomez.fragmentcommunicationkotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class RecyclerViewFragment : Fragment() {

    private lateinit var personSelectedCallback: OnPersonSelectedListener
    private lateinit var recyclerView: RecyclerView
    private lateinit var personList: MutableList<Person>

    interface OnPersonSelectedListener {
        fun onPersonSelected(person: Person)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            personSelectedCallback = context as OnPersonSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString() + "must implement OnPersonSelectedListener")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        personList = ArrayList()
        personList.add(Person("Jerry", "30", "Sloth"))
        personList.add(Person("Jones", "55", "Bear"))
        personList.add(Person("Anna", "14", "Cat"))
        personList.add(Person("Jay", "23", "Butterfly"))
        personList.add(Person("YoMomma", "45", "Tiger"))
        personList.add(Person("YoDaddy", "45", "Lion"))
        personList.add(Person("YoBaldHeadedGranny", "80", "Dog"))

        val rvAdapter = RecyclerViewAdapter(personList, personSelectedCallback)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = linearLayoutManager
        }
    }
}
