package com.demo

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.wiremock.spring.ConfigureWireMock
import org.wiremock.spring.EnableWireMock
import kotlin.test.assertEquals


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
    fun `should return mocked weather data`() {
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
}
