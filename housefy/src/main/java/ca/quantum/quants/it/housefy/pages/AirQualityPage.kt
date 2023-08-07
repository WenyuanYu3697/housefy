package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.util.Log
import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.CurrentDataIndexLocal
import ca.quantum.quants.it.housefy.EnvironmentDataListLocal
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.air_quality.AQICategory
import ca.quantum.quants.it.housefy.components.air_quality.AQICategoryCard
import ca.quantum.quants.it.housefy.components.common.IndicatorGraph
import ca.quantum.quants.it.housefy.models.EnvironmentData
import ca.quantum.quants.it.housefy.network.fetchEnvironmentData
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.Green
import ca.quantum.quants.it.housefy.ui.theme.Orange
import ca.quantum.quants.it.housefy.ui.theme.Yellow
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun AirQualityPage() {
    val environmentDataList = EnvironmentDataListLocal.current
    val currentDataIndex = CurrentDataIndexLocal.current
    val currentData = environmentDataList.getOrNull(currentDataIndex)

    val aqiCategories =
        listOf(
            AQICategory(
                stringResource(R.string.excellent),
                stringResource(R.string.excellent_description),
                Green,
            ),
            AQICategory(
                stringResource(R.string.good),
                stringResource(R.string.good_description),
                Yellow,
            ),
            AQICategory(
                stringResource(R.string.moderate),
                stringResource(R.string.moderate_description),
                Orange,
            ),
            AQICategory(
                stringResource(R.string.poor),
                stringResource(R.string.poor_description),
                Red,
            ),
        )

    val value = currentData?.let {
        calculateAQI(it.co2).coerceAtMost(100)
    } ?: 0


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundGrey)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            IndicatorGraph(
                indicatorValue = value,
                foregroundIndicatorColor = getAQIColor(value),
                maxIndicatorValue = 100,
                indicatorText = {
                    Text(
                        text = "$value",
                        color = getAQIColor(value),
                        fontSize = 84.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset(y = (-8).dp),
                    )
                }
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        items(aqiCategories) { category ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                AQICategoryCard(category)
            }
        }

    }
}

fun calculateAQI(co2: Float): Int {
    return (co2 / 10).roundToInt()
}

fun getAQIColor(aqi: Int): Color {
    return when (aqi) {
        in 0..25 -> Color(0xFF8CD456)
        in 26..50 -> Color(0xFFFFE24C)
        in 51..75 -> Color(0xFFFFA500)
        else -> Color(0xFFFF0000)
    }
}