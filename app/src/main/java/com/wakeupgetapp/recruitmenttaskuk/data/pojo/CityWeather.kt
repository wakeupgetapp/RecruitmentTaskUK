package com.wakeupgetapp.recruitmenttaskuk.data.pojo

import com.google.gson.annotations.SerializedName
import com.wakeupgetapp.recruitmenttaskuk.data.TemperatureAtHour
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

data class CityWeather(

    @SerializedName("city")
    var city: String,

    @SerializedName("weather")
    var weather: String,

    @SerializedName("hourly_temp")
    var hourlyTemp: List<TemperatureAtHour>

)