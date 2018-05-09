package com.example.amin_elhasan.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val searchTerm = intent.extras.getString("searchTerm")

        //Get a reference to the listview
        var listView = findViewById<ListView>(R.id.listViewForecast)

        //Its a var beacause the forecast changes everyday, mutable means we want it to changes
        var forecastStrings = mutableListOf<String>()

        var forecasts = response.body()?.query?.results?.channel?.item?.forecast

        if (forecasts != null) {
            for (forecast in forecasts) {
                val newString = "${forecast.date} - High: ${forecast.high} Low: ${forecast.low} - ${forecast.text}"
                forecastStrings.add(newString)
            }
        }

        //Create an ArrayAdapter to have the data show up in the ListView
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, familyMembers)

        listView.adapter = adapter

        val retriever = WeatherHelp.WeatherRetriever()

        // Set up a callback
        val callback = object : Callback<WeatherHelp.Weather> {
            fun onResponse(call: retrofit2.Call<WeatherHelp.Weather>?, response: retrofit2.Response<WeatherHelp.Weather>?) {
                title = response?.body()?.query?.results?.channel?.title
            }

            fun onFailure(call: Call<WeatherHelp.Weather>?, t: Throwable?) {
                println("Sorry, it failed")
            }

        }
            retriever.getForecast(callback, searchTerm)
        }

}
