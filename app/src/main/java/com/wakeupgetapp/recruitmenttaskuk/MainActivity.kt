package com.wakeupgetapp.recruitmenttaskuk

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather
import com.wakeupgetapp.recruitmenttaskuk.ui.navigation.NavigationScreens
import com.wakeupgetapp.recruitmenttaskuk.ui.theme.RecruitmentTaskUKTheme
import com.wakeupgetapp.recruitmenttaskuk.ui.views.CityListView
import com.wakeupgetapp.recruitmenttaskuk.ui.views.MainView

class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        printLogs(sharedViewModel.cityWeatherList)

        setContent {
            RecruitmentTaskUKTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = NavigationScreens.CityListScreen.title){
                    composable(NavigationScreens.CityListScreen.title){
                        CityListView(sharedViewModel, navController)
                    }
                }
            }
        }
    }

    private fun printLogs(cityWeatherList: List<CityWeather>) {

//      1. Find the smallest temperature across all cities and print it
        Log.d("Lowest Temperature", Tasks(cityWeatherList).getLowestTemp().toString())

//      2. For each city find its highest temperatures and print the results in format "city: max_temp",
        Log.d("D", "Highest temperature of each city: ")
        Tasks(cityWeatherList).getHighestTempEachCity().forEach {
            Log.d(it.key, it.value.toString())
        }

//      3. Find the city with the smallest average daily temperature and print its name
        Log.d("Lowest Average Temperature", Tasks(cityWeatherList).getCityWithLowestAverageTemp())
    }


}

