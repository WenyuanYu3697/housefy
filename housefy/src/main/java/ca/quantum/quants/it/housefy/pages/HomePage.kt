package ca.quantum.quants.it.housefy.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.components.home.DevicesList
import ca.quantum.quants.it.housefy.components.home.EnergyUsageCard
import ca.quantum.quants.it.housefy.components.home.TemperatureCard

@Composable
fun HomePage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(16.dp),
    ) {
        item {
            TemperatureCard()
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            EnergyUsageCard()
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            DevicesList()
        }
    }
}
