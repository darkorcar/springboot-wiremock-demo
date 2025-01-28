package com.demo

class WeatherServiceClient {

    fun getWeather(city: String): String {
        return """{"temperature": "15°C"}"""
    }

}