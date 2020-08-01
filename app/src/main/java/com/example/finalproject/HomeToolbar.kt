package com.example.finalproject

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.geo.GeoHome

open class HomeToolbar : AppCompatActivity() {
    /**
     * @return Boolean
     * inflate the menu into layout
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_toolbar_menu, menu)
        return true
    }

    /**
     * @return Boolean
     * action when specific item is selected in toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.home_tb_geo -> startActivity(Intent(applicationContext, GeoHome::class.java))
        }

        return true
    }

}