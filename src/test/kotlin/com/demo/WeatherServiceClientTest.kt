package com.demo

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WeatherServiceClientTest {

    private val weatherServiceClient: WeatherServiceClient = WeatherServiceClient()

    @Test
    fun `should return mocked weather data`() {
        val response = weatherServiceClient.getWeather("London")
        assertEquals("""{"temperature": "15°C"}""", response)
    }
}
