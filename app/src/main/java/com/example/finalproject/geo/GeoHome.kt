package com.example.finalproject.geo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.finalproject.R
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class GeoHome : AppCompatActivity() {

    /**
     * API KEY for GEO DATA
     */
    val API_KEY = "5ZQASPPOES5L8XVSNWG2R3JPPAJWEMMB"

    /**
     * Input for latitude
     */
    private lateinit var latInput: EditText

    /**
     * input for longitude
     */
    private lateinit var lngInput: EditText

    /**
     * ListVIew of all the cities
     */
    lateinit var cityListView : ListView

    /**
     * Progress bar that indicate the progress of fetching city list from API
     */
    lateinit var geoProgress : ProgressBar

    /**
     * Shared Preferences to stored value into local storage
     */
    lateinit var sharedPref: SharedPreferences

    /**
     * list of all the cites
     */
    lateinit var cities :List<City>

    /**
     * starting to get the list of cities when user hit the button
     */
    fun onSearchGeo(v: View) {
        if (latInput.text.toString() == "" || lngInput.text.toString() == "") {
            Toast.makeText(v.context, "latitude and longitude can not be empty",Toast.LENGTH_LONG).show()
            return
        }
        cityListView.visibility = View.INVISIBLE
        geoProgress.visibility=View.VISIBLE
        fetchJson()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_home)
        latInput = findViewById(R.id.latInput);
        lngInput = findViewById(R.id.longInput);
        cityListView = findViewById(R.id.citiListView)
        geoProgress = findViewById(R.id.geoProgress)

        geoProgress.visibility=View.INVISIBLE

        sharedPref = getPreferences(Context.MODE_PRIVATE)

        latInput.hint = sharedPref.getString("savedLat","45.4215")
        lngInput.hint =sharedPref.getString("savedLng", "-75.6972")

        cityListView.setOnItemClickListener { parent, view, position, id ->
            detailDialog(position)
        }
    }


    override fun onPause() {
        super.onPause()
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("savedLat",latInput.text.toString())
            putString("savedLng", lngInput.text.toString())
            apply()
        }
    }

    /**
     * handle http request, fetch json and parse into ojbects
     */
    private fun fetchJson() {

        val url = "https://api.geodatasource.com/cities?key=$API_KEY&format=json&lat=${latInput.text}&lng=${lngInput.text}"
        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
               cities  = gson.fromJson(body, Array<City>::class.java).toList()

                runOnUiThread {
                    cityListView.adapter = CitiesListAdapter(cities)
                    cityListView.visibility = View.VISIBLE
                    geoProgress.visibility=View.GONE
                }

            }

        })
    }

    /**
     * set up dialog screen
     */
    private fun detailDialog(index: Int){
        val dialog = layoutInflater.inflate(R.layout.geo_detail_dialog,null)
        val dCountry= dialog.findViewById<TextView>(R.id.dCoutry)
        val dRegion= dialog.findViewById<TextView>(R.id.dRegion)
        val dCity= dialog.findViewById<TextView>(R.id.dCity)
        val dCurrency= dialog.findViewById<TextView>(R.id.dCurrency)
        val dLatitude= dialog.findViewById<TextView>(R.id.dLatitude)
        val dLongitude= dialog.findViewById<TextView>(R.id.dLongitude)

        val city = cities[index]

        dCountry.text=city.country
        dRegion.text=city.region
        dCity.text=city.city
        dCurrency.text=city.currency
        dLatitude.text=city.latitude
        dLongitude.text=city.longitude

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton(R.string.d_pos_btn){dialog, which ->
            val webIntent = Uri.parse("https://maps.google.com/?q=${city.latitude},${city.longitude}").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
                .setNegativeButton(R.string.d_nev_btn){dialog, which -> dialog.cancel()}
                .setView(dialog)
        builder.create().show()
    }
}

/**
 * class of City with demanded attribute from JSON
 */
class City(val country:String, val region:String, val city: String,
           val currency: String, val latitude:String,val longitude:String)