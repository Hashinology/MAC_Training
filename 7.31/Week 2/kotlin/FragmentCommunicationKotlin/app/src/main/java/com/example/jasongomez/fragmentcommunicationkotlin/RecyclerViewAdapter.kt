package com.example.jasongomez.fragmentcommunicationkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewAdapter(var personList: List<Person>, var personSelectedListener: RecyclerViewFragment.OnPersonSelectedListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personTemp = personList[position]
        holder.name.text = personTemp.name
        holder.age.text = personTemp.age
        holder.favoriteAnimal.text = personTemp.favoriteAnimal
        holder.itemView.setOnClickListener { personSelectedListener.onPersonSelected(personTemp) }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvName)
        var age: TextView = itemView.findViewById(R.id.tvAge)
        var favoriteAnimal: TextView = itemView.findViewById(R.id.tvFavoriteAnimal)

    }
}