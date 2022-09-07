package com.asha.playerlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*



const val url="https://login.microsoftonline.com/c6ff8a91-418b-46d6-84c8-080ef0fd3544/"
private var weatherData: TextView? = null

private var StringBuilder:StringBuilder?  = null
private val sharedPrefFile = "kotlinsharedpreference"
var sharedPreferences:SharedPreferences ?= null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData  = findViewById<TextView>(R.id.tokenRes);

        findViewById<View>(R.id.button_calc).setOnClickListener { getAccesToken() }
        findViewById<View>(R.id.button_calc).setOnClickListener { goto () }
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
       // weatherData!!.text = StringBuilder
        //getAccesToken()


    }

    private fun goto() {
        val intent = Intent(this, MainActivity2::class.java)
         startActivity(intent)

    }


    private fun getAccesToken() {

        val retrofit = Retrofit.Builder().
        baseUrl(url).addConverterFactory(GsonConverterFactory.create())
            .build()
        val service=retrofit.create(MyInterface::class.java)
        val call =service.getToken("b41ae22d-7539-41bb-b2cb-90e6ed2fc7c5","9n2dj_3Vwy_eLd8WDUAW-rZdTuBO~qPNTF",
        "client_credentials","https://alnahdico-test.sandbox.operations.dynamics.com/.default","application/x-www-form-urlencoded")
        call.enqueue(object :Callback<AccessToken>{
            override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                if(response?.code()==200)
                {
                    val tokenResponse = response.body()!!
                    val editor:SharedPreferences.Editor =  sharedPreferences!!.edit()
                    editor.putString("access_token",tokenResponse.access_token)
                    editor.apply()
                    editor.commit()
                }
                else{
                   // weatherData!!.text = response?.code().toString()

                }
            }
            override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                //textView!!.text = t.message
            }
        })

    }


}