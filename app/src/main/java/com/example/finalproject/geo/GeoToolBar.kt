package com.example.finalproject.geo

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R

open class GeoToolBar: AppCompatActivity(){
    /**
     * @return Boolean
     * inflate the menu into layout
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.geo_toolbar_menu,menu)
        return true
    }
    /**
     * @return Boolean
     * action when specific item is selected in toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tbABout ->{
                val about = layoutInflater.inflate(R.layout.geo_about_dialog, null)

                val builder = AlertDialog.Builder(this)
                builder.setMessage("About")
                        .setPositiveButton("OK") { dialog, which -> }
                        .setView(about)
                builder.create().show()
            }
            R.id.tbSaved ->{
                startActivity(Intent(applicationContext, SavedCities::class.java))
            }
        }
        return true
    }
}