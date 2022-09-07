package com.asha.playerlist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private var weatherData2: TextView? = null
    private val sharedPrefFile = "kotlinsharedpreference"
    var sharedPreferences: SharedPreferences?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        weatherData2  = findViewById<TextView>(R.id.tokenRes2);
        findViewById<View>(R.id.button_calc2).setOnClickListener { getShaedPref() }
        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
    }
    private fun getShaedPref() {
        weatherData2!!.text = sharedPreferences!!.getString("access_token","")
    }
}