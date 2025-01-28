package com.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WeatherServiceClient(private val restTemplate: RestTemplate) {

    @Value("\${weather.api.url}")
    private lateinit var weatherApiUrl: String

    fun getWeather(city: String): String {
        val url = "$weatherApiUrl/weather?city=$city"
        return restTemplate.getForObject(url, String::class.java) ?: "No data available"
    }

}