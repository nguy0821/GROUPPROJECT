package com.example.finalproject.geo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.finalproject.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GeoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GeoFragment : Fragment() {


    var isTablet = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fm =  inflater.inflate(R.layout.fragment_geo, container, false)
        val arg = arguments!!

        val fmCountry = fm.findViewById<TextView>(R.id.fm_country)
        val fmRegion = fm.findViewById<TextView>(R.id.fm_region)
        val fmCity = fm.findViewById<TextView>(R.id.fm_city)
        val fmCurrency = fm.findViewById<TextView>(R.id.fm_currency)
        val fmLatitude = fm.findViewById<TextView>(R.id.fm_latitude)
        val fmLongitude = fm.findViewById<TextView>(R.id.fm_longitude)

        val saveBtn = fm.findViewById<Button>(R.id.fm_saveBtn)
        val mapBtn = fm.findViewById<Button>(R.id.fm_mapBtn)

        fmCountry.text = arg.getString("fmCountry")
        fmRegion.text = arg.getString("fmRegion")
        fmCity.text = arg.getString("fmCity")
        fmCurrency.text = arg.getString("fmCurrency")
        fmLatitude.text = arg.getString("fmLatitude")
        fmLongitude.text = arg.getString("fmLongitude")

        if(isTablet){
            val geo = (activity as GeoHome)
            saveBtn.setOnClickListener  {e -> geo.saveCity(arg.getInt("index"))}

            mapBtn.setOnClickListener { e ->
                val webIntent = Uri.parse("https://maps.google.com/?q=${arg.getString("fmLatitude")},${arg.getString("fmLongitude")}").let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
            }

        }
        return fm
    }

}