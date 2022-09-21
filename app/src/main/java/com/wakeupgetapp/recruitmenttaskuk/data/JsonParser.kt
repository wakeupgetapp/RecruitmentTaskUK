package com.wakeupgetapp.recruitmenttaskuk.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather
import org.json.JSONArray
import java.lang.reflect.Type
import java.util.ArrayList

class JsonParser {
    fun getData(jsonString: String): List<CityWeather> {
        val collectionType: Type = object : TypeToken<List<CityWeather>>() {}.type

        return Gson()
            .fromJson(jsonString, collectionType) as List<CityWeather>
    }
}