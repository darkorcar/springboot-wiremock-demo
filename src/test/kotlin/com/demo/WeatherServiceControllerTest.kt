package com.demo

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(WeatherServiceController::class)
class WeatherServiceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return weather data for a given city`() {
        val city = "London"

        mockMvc.perform(get("/weather").param("city", city))
            .andExpect(status().isOk)
            .andExpect(content().string("""{"temperature": "15°C"}"""))
    }

}