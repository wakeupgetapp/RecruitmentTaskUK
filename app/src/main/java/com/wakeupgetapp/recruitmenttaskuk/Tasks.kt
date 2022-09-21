package com.wakeupgetapp.recruitmenttaskuk

import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather

class Tasks(private val cityWeatherList: List<CityWeather>) {

    fun getLowestTemp(): Double =
        cityWeatherList.map { it -> it.hourlyTemp.map { it.temp }.min() }.min()

    fun getHighestTempEachCity(): Map<String, Double> =
        cityWeatherList.associateBy(keySelector = { it.city },
            valueTransform = { it -> it.hourlyTemp.map { it.temp }.max() })

    fun getCityWithLowestAverageTemp(): String =
        cityWeatherList.associateBy(keySelector = { it.city },
            valueTransform = { it -> it.hourlyTemp.map { it.temp }.average() })
            .minBy { it.value }.key

}