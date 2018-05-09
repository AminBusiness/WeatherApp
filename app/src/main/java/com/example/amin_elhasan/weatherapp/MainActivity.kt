package com.example.amin_elhasan.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecastButton = findViewById<Button>(R.id.buttonGetForecast)

        getForecastButton.setOnClickListener()
        {
            var forecastIntent = Intent(this, ForecastActivity::class.java)
            val searchEditText = findViewById<EditText>(R.id.editTextCity)
            forecastIntent.putExtra("searchTerm", editTextCity.text.toString())
            startActivity(forecastIntent)
        }
    }
}
