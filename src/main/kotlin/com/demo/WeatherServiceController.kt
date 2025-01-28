package com.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

@RestController
class WeatherServiceController {

    private val weatherServiceClient: WeatherServiceClient = WeatherServiceClient()

    @GetMapping("/weather")
    fun weather(@RequestParam("city") city: String): String {
        return weatherServiceClient.getWeather(city)
    }

}
