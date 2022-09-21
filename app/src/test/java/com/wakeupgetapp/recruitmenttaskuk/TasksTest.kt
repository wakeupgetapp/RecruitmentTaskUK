package com.wakeupgetapp.recruitmenttaskuk

import com.wakeupgetapp.recruitmenttaskuk.data.JsonParser
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TasksTest {
    private lateinit var tasks: Tasks
    private lateinit var tasks2: Tasks

    @Before
    fun setUp() {
        tasks = Tasks(JsonParser().getData(json))
    }

    @Test
    fun getLowestTempTest() {
        val lowestTemp = tasks.getLowestTemp()
        Assert.assertTrue(lowestTemp == -6.0)
        Assert.assertFalse(lowestTemp == -0.0)
    }

    @Test
    fun getHighestTempEachCityTest() {
        val highestTemp = tasks.getHighestTempEachCity()
        Assert.assertFalse(highestTemp["Berlin"] == -6.0)
        Assert.assertTrue(highestTemp["Paris"] == 22.0)
    }

    @Test
    fun getCityWithLowestAverageTempTest() {
        Assert.assertTrue(tasks.getCityWithLowestAverageTemp() == "Warsaw")
        Assert.assertFalse(tasks.getCityWithLowestAverageTemp() == "Paris")
    }

    //modified JSON
    @Before
    fun setUp2() {
        tasks2 = Tasks(JsonParser().getData(json2))
    }

    @Test
    fun getLowestTempTest2() {
        val lowestTemp = tasks2.getLowestTemp()
        Assert.assertFalse(lowestTemp == -6.0)
        Assert.assertTrue(lowestTemp == -400.0)
    }

    @Test
    fun getHighestTempEachCityTest2() {
        val highestTemp = tasks2.getHighestTempEachCity()
        Assert.assertFalse(highestTemp["Berlin"] == -4.0)
        Assert.assertTrue(highestTemp["New York"] == 25.0)
    }

    @Test
    fun getCityWithLowestAverageTempTest2() {
        Assert.assertTrue(tasks2.getCityWithLowestAverageTemp() == "Berlin")
        Assert.assertFalse(tasks2.getCityWithLowestAverageTemp() == "Paris")
    }


    val json = """
    [
       {
          "city":"Warsaw",
          "weather":"rainy",
          "hourly_temp":[
             { "temp":-2, "hour":0 },
             { "temp":-2, "hour":4 },
             { "temp":0.5, "hour":8 },
             { "temp":2, "hour":12 },
             { "temp":3, "hour":16 },
             { "temp":-1, "hour":20 }
          ]
       },
       {
          "city":"Paris",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":11, "hour":0 },
             { "temp":14, "hour":4 },
             { "temp":18, "hour":8 },
             { "temp":22, "hour":12 },
             { "temp":15, "hour":16 },
             { "temp":13, "hour":20 }
          ]
       },
       {
          "city":"Berlin",
          "weather":"sunny",
          "hourly_temp":[
             { "temp":-6, "hour":0 },
             { "temp":-4, "hour":4 },
             { "temp":2, "hour":8 },
             { "temp":4, "hour":12 },
             { "temp":5.5, "hour":16 },
             { "temp":3, "hour":20 }
          ]
       },
       {
          "city":"New York",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":12, "hour":0 },
             { "temp":13, "hour":4 },
             { "temp":12, "hour":8 },
             { "temp":15, "hour":12 },
             { "temp":16, "hour":16 },
             { "temp":14, "hour":20 }
          ]
       }
    ]
""".trimIndent()

    private val json2 = """
    [
       {
          "city":"Warsaw",
          "weather":"rainy",
          "hourly_temp":[
             { "temp":-2, "hour":0 },
             { "temp":-2, "hour":4 },
             { "temp":0.5, "hour":8 },
             { "temp":2, "hour":12 },
             { "temp":3, "hour":16 },
             { "temp":-1, "hour":20 }
          ]
       },
       {
          "city":"Paris",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":11, "hour":0 },
             { "temp":14, "hour":4 },
             { "temp":18, "hour":8 },
             { "temp":22, "hour":12 },
             { "temp":15, "hour":16 },
             { "temp":25, "hour":20 }
          ]
       },
       {
          "city":"Berlin",
          "weather":"sunny",
          "hourly_temp":[
             { "temp":-6, "hour":0 },
             { "temp":-400, "hour":4 },
             { "temp":2, "hour":8 },
             { "temp":4, "hour":12 },
             { "temp":5.5, "hour":16 },
             { "temp":3, "hour":20 }
          ]
       },
       {
          "city":"New York",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":12, "hour":0 },
             { "temp":13, "hour":4 },
             { "temp":12, "hour":8 },
             { "temp":25, "hour":12 },
             { "temp":16, "hour":16 },
             { "temp":14, "hour":20 }
          ]
       }
    ]
""".trimIndent()
}



