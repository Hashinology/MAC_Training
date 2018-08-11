package com.example.jasongomez.fragmentcommunicationkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var favoriteAnimal: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_fragment, container, false)
        name = view.findViewById(R.id.tvName)
        age = view.findViewById(R.id.tvAge)
        favoriteAnimal = view.findViewById(R.id.tvFavoriteAnimal)
        return view
    }

    fun personSelected(person: Person) {
        name.text = person.name
        age.text = person.age
        favoriteAnimal.text = person.favoriteAnimal

    }
}