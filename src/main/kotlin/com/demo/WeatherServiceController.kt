package com.demo;


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

@RestController
class WeatherServiceController {

    @GetMapping("/weather")
    fun weather(@RequestParam("city") city: String): String {
        return """{"temperature": "15°C"}"""
    }

}
