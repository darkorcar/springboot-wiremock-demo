package com.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals


@SpringBootTest
class WeatherServiceClientTest {

    @Autowired
    private lateinit var weatherServiceClient: WeatherServiceClient

    @Test
    fun `should return mocked weather data`() {
        val response = weatherServiceClient.getWeather("London")
        assertEquals("""{"temperature": "15°C"}""", response)
    }
}
