package com.demo.weatherclient

import com.demo.watherclient.WeatherServiceClient
import com.demo.watherclient.WeatherServiceClientException
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.wiremock.spring.ConfigureWireMock
import org.wiremock.spring.EnableWireMock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@SpringBootTest
@EnableWireMock(
    ConfigureWireMock(
        name = "weather-api",
        baseUrlProperties = ["weather.api.url"]
    )
)
class WeatherServiceClientTest {

    @Autowired
    private lateinit var weatherServiceClient: WeatherServiceClient

    @Test
    fun `should fetch weather data`() {
        stubFor(
            get(urlPathEqualTo("/weather"))
                .withQueryParam("city", equalTo("London"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody("""{"temperature": "15°C"}""")
                        .withHeader("Content-Type", "application/json")
                )
        )

        val response = weatherServiceClient.getWeather("London")
        assertEquals("""{"temperature": "15°C"}""", response)
    }

    @Test
    fun `should handle error response from weather api`() {
        stubFor(
            get(urlPathEqualTo("/weather"))
                .withQueryParam("city", equalTo("London"))
                .willReturn(
                    aResponse()
                        .withStatus(500)
                        .withBody("Weather service unavailable")
                )
        )

        val exception = try {
            weatherServiceClient.getWeather("London")
            null
        } catch (e: WeatherServiceClientException) {
            e
        }

        assertNotNull(exception?.message)
    }

    @Test
    fun `should handle no data from weather api`() {

        stubFor(
            get(urlPathEqualTo("/weather"))
                .withQueryParam("city", equalTo("London"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody("")

                )
        )

        val response = weatherServiceClient.getWeather("London")
        assertEquals("No data available for city: London", response)
    }
}
