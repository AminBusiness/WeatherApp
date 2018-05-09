package com.example.amin_elhasan.weatherapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Amin_Elhasan on 3/21/2018.
 */
interface WeatherHelp {
    @GET("bins/1drchv")
            //fun is short for function
    fun getForecast(): Call<Weather>


//class Forecast(val high: String, val low: String)

    class Weather(val query: WeatherQuery) //Class holds a query that we're referring to as query
    class WeatherQuery(val results: WeatherResult)
    class WeatherResult(val channel: WeatherChannel)
    class WeatherChannel(var title: String, val item: WeatherItem)
    class WeatherItem(val forecast: List<Forecast>)
    class Forecast(val date: String, val day: String, val high: String, val low: String, val text: String)

    class WeatherRetriever { // Val is a constant so the information can not be changed
        val service: WeatherHelp

        init  //Mini constructor short for initialize
        {
            val retrofit = retrofit2.Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

            service = retrofit.create(WeatherHelp::class.java)
        }

        fun getForecast(callback: Callback<Weather>, searchTerm: String) {
            var call = service.getForecast()
            call.enqueue(callback) //Do this in the background, so we dont have to wait for anything
        }
    }
}