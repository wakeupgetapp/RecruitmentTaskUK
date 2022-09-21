package com.wakeupgetapp.recruitmenttaskuk.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wakeupgetapp.recruitmenttaskuk.R
import com.wakeupgetapp.recruitmenttaskuk.SharedViewModel
import com.wakeupgetapp.recruitmenttaskuk.data.TemperatureAtHour
import com.wakeupgetapp.recruitmenttaskuk.data.pojo.CityWeather
import com.wakeupgetapp.recruitmenttaskuk.ui.theme.RecruitmentTaskUKTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CityListView(sharedViewModel: SharedViewModel, navController: NavHostController) {

    val cityWeather = sharedViewModel.cityWeatherList
    val cardsExpanded = remember {
        mutableStateListOf<Boolean>(elements = Array(cityWeather.size) { false })
    }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            items(cityWeather.size) { index ->
                CityCard(cityWeather[index], cardsExpanded[index], onClick = {
                    cardsExpanded[index] = cardsExpanded[index] != true
                })
            }
        }
}

@Composable
fun CityCard(cityWeather: CityWeather, expanded: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Row(
                Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = cityWeather.city,
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = getClosestHour(cityWeather.hourlyTemp).temp.toString() + "\u2103",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(getWeatherPainter(cityWeather.weather)),
                    contentDescription = null,
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp)
                )
            }

            if (expanded) {
                Divider(color = Color.LightGray, thickness = 1.dp)
                Column(
                    Modifier
                        .wrapContentHeight()
                        .background(color = MaterialTheme.colors.secondaryVariant)
                ) {
                    var closestHour = getClosestHour(cityWeather.hourlyTemp)
                    cityWeather.hourlyTemp.forEach {
                        TemperatureCard(
                            hour = it.hour,
                            temperature = it.temp,
                            closestHour = closestHour.hour
                        )


                    }
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick)
                    .background(color = MaterialTheme.colors.primaryVariant)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.expand_arrow),
                    contentDescription = null,
                    Modifier
                        .size(24.dp)
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.Bottom)
                        .align(Alignment.CenterHorizontally)
                        .rotate(if (expanded) 180f else 0f)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}


fun getWeatherPainter(weather: String): Int {
    return when (weather) {
        "sunny" -> R.drawable.clear_day
        "rainy" -> R.drawable.rainy
        "cloudy" -> R.drawable.cloudy
        else -> R.drawable.partly_cloudy
    }
}

fun getClosestHour(hourlyTemp: List<TemperatureAtHour>): TemperatureAtHour {
    val currHour = SimpleDateFormat("HH", Locale.UK).format(Date())
    return hourlyTemp.filter { it.hour >= currHour.toInt() }
        .minByOrNull { it.hour - currHour.toInt() } ?: hourlyTemp[2]
}

@Composable
fun TemperatureCard(hour: Int, temperature: Double, closestHour: Int) {
    val color = if (closestHour == hour){
        MaterialTheme.colors.primaryVariant
    } else {
        MaterialTheme.colors.secondaryVariant
    }
    Surface(
        Modifier
            .height(36.dp)
            .background(color = color)
            .padding(start = 12.dp, end = 12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = color)
                .wrapContentHeight(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$temperature\u2103")
            Text(text = "$hour:00")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityListPreview() {
    RecruitmentTaskUKTheme {
        CityListView(
            sharedViewModel = SharedViewModel(),
            navController = NavHostController(LocalContext.current)
        )
    }
}