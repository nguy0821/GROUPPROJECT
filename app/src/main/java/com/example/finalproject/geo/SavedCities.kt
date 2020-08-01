package com.example.finalproject.geo

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.finalproject.R
import com.google.android.material.snackbar.Snackbar


class SavedCities : AppCompatActivity() {

    lateinit var dbOpener : CityDB
    lateinit var db: SQLiteDatabase

    lateinit var cities: ArrayList<City>
    lateinit var savedCityList : ListView
    lateinit var cityAdapter: SavedCityAdapter

    lateinit var sb: Snackbar


    fun onRemoveRow(v: View){
        sb = Snackbar.make(v, "You want to Delete?", Snackbar.LENGTH_LONG)
                .setAction("OK"){e ->
                    val parent = v.parent as ConstraintLayout

                    deleteARow(parent.tag as Int)
                }
        sb.show()

    }

    fun  deleteARow(id : Int){
        val city = cities[id]

        db.delete(CityDB.TABLE_NAME, " id = ?", arrayOf(city.dbId))

        cities.removeAt(id)
        cityAdapter.notifyDataSetChanged()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_cities)
        savedCityList = findViewById(R.id.savedCityList)
        dbOpener= CityDB(applicationContext)
        db=dbOpener.writableDatabase


        cities = ArrayList()
        cityAdapter = SavedCityAdapter( cities)


        val result = db.rawQuery("SELECT * FROM  " + CityDB.TABLE_NAME, null)

        while (result.moveToNext()){
            cities.add(City(
                    result.getString(result.getColumnIndex(CityDB.COL_COUNTRY)),
                    result.getString(result.getColumnIndex(CityDB.COL_REGION)),
                    result.getString(result.getColumnIndex(CityDB.COL_CITY)),
                    result.getString(result.getColumnIndex(CityDB.COL_CURRENCY)),
                    result.getString(result.getColumnIndex(CityDB.COL_LATITUDE)),
                    result.getString(result.getColumnIndex(CityDB.COL_LONGITUDE)),
                    result.getString(result.getColumnIndex(CityDB.COL_ID))
            ))
        }
        savedCityList.adapter = cityAdapter
    }
}

class SavedCityAdapter(val cities: ArrayList<City>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.geo_saved_city_row, parent, false)

        val text = view.findViewById<TextView>(R.id.savedRowText)
        text.text = cities[position].city
        view.tag= position
        return view
    }

    override fun getItem(p0: Int): Any {
        return cities[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cities.size
    }

}