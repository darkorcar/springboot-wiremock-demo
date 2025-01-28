package com.demo

import org.springframework.stereotype.Service

@Service
class WeatherServiceClient {

    fun getWeather(city: String): String {
        return """{"temperature": "15°C"}"""
    }

}