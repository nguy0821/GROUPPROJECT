package com.example.finalproject.geo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CityDB(context: Context) : SQLiteOpenHelper(context, "CityDB", null, 3){

    companion object{
        val TABLE_NAME="cities"
        val COL_ID = "id"
        val COL_COUNTRY="country"
        val COL_REGION="region"
        val COL_CITY="city"
        val COL_CURRENCY="currency"
        val COL_LATITUDE="latitude"
        val COL_LONGITUDE="longitude"
    }

    val sqlCreateTable = "CREATE TABLE $TABLE_NAME (" +
            "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COL_COUNTRY TEXT, $COL_REGION TEXT," +
            "$COL_CITY TEXT, $COL_CURRENCY TEXT," +
            "$COL_LATITUDE TEXT, $COL_LONGITUDE TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(sqlCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL(sqlCreateTable)
    }
}