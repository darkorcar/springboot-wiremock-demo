package com.demo;


import com.demo.watherclient.WeatherServiceClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

@RestController
class WeatherServiceController {

    @Autowired
    private lateinit var weatherServiceClient: WeatherServiceClient

    @GetMapping("/weather")
    fun weather(@RequestParam("city") city: String): String {
        return try {
            weatherServiceClient.getWeather(city)
        }
        catch (e: Exception) {
            "Error calling weather data service: ${e.message}"
        }
    }

}
