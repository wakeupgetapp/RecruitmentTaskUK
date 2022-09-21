package com.wakeupgetapp.recruitmenttaskuk

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wakeupgetapp.recruitmenttaskuk.data.DataSource
import com.wakeupgetapp.recruitmenttaskuk.data.JsonParser
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather

import org.json.JSONArray
import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Type
import java.util.ArrayList

class JsonParserTest {

    @Test
    fun jsonParse(){
        val jsonParser = JsonParser()
        val parsedJson = jsonParser.getData(json)
        Assert.assertTrue(parsedJson[0].hourlyTemp[0].temp == -2.0)
        Assert.assertFalse(parsedJson[2].hourlyTemp[1].hour == 8)
    }

    @Test
    fun jsonParse2(){
        val jsonParser = JsonParser()
        val parsedJson = jsonParser.getData(json2)
        Assert.assertTrue(parsedJson.size == 2)
        Assert.assertFalse(parsedJson[0].hourlyTemp.size == 4)
        Assert.assertFalse(parsedJson.size == 4)

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
       }
    ]
""".trimIndent()
}