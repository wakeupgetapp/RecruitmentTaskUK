package com.wakeupgetapp.recruitmenttaskuk

import android.util.Log
import androidx.lifecycle.ViewModel
import com.wakeupgetapp.recruitmenttaskuk.data.Repository
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class SharedViewModel : ViewModel() {

    var cityWeatherList: List<CityWeather> = Repository().getData()


}