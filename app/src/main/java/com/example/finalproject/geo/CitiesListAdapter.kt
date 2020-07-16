package com.example.finalproject.geo

import android.util.Log
import android.util.Log.println
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.finalproject.R

/**
 * Adapter for the list view of all the cities
 */
class CitiesListAdapter(private val cities : List<City>): BaseAdapter() {
    override fun getView(position: Int, v: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.city_row,parent,false)
        val  cityRowName = view.findViewById<TextView>(R.id.cityRowName)

        cityRowName.text = cities[position].city
        return view
    }

    override fun getItem(p: Int): Any {
        return cities[p]
    }

    override fun getItemId(p: Int): Long {
        return  p.toLong()
    }

    override fun getCount(): Int {
        return cities.size
    }
}