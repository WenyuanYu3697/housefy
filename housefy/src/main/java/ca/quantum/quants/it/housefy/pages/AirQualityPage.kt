package ca.quantum.quants.it.housefy.pages

import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.components.air_quality.AQICategory
import ca.quantum.quants.it.housefy.components.air_quality.AQICategoryCard
import ca.quantum.quants.it.housefy.components.air_quality.AirQualityGraph
import ca.quantum.quants.it.housefy.components.air_quality.getAQIColor

@Composable
fun AirQualityPage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            var value by remember {
                mutableStateOf(20)
            }

            AirQualityGraph(
                indicatorValue = value,
                foregroundIndicatorColor = getAQIColor(value)
            )
        }

        val aqiCategories = listOf(
            AQICategory(
                "Excellent",
                "Air quality is satisfactory with minimal or no risk of air pollution.",
                Color(0xFF8CD456),
            ),
            AQICategory(
                "Good",
                "Air quality is acceptable. Some pollutants may have a moderate impact on a small number of sensitive individuals.",
                Color(0xFFFFE24C),
            ),
            AQICategory(
                "Moderate",
                "Some sensitive individuals may experience health effects. The general public is unlikely to be affected.",
                Color(0xFFFFA500),
            ),
            AQICategory(
                "Poor",
                "Health effects may occur in the general public, and sensitive groups may experience more severe health issues.",
                Color(0xFFFF0000),
            ),
        )

        item { Spacer(modifier = Modifier.height(24.dp)) }

        items(aqiCategories) { category ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                AQICategoryCard(category)
            }
        }

    }
}