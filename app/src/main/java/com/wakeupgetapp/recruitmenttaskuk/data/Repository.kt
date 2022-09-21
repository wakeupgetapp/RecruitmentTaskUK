package com.wakeupgetapp.recruitmenttaskuk.data

import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather

class Repository {
    fun getData(): List<CityWeather> {
       val json = DataSource().json
       return JsonParser().getData(json)
    }
}