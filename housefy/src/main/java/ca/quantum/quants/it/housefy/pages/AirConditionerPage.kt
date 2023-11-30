package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.LocalAirConditionerAmbient
import ca.quantum.quants.it.housefy.LocalEnvironmentData
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.common.IndicatorGraph
import ca.quantum.quants.it.housefy.components.common.SensorValue
import ca.quantum.quants.it.housefy.components.common.StateSwitcher
import ca.quantum.quants.it.housefy.network.AirConditionerStatus
import ca.quantum.quants.it.housefy.network.updateAirConditionerStatus
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.TextBlack
import kotlinx.coroutines.launch

@Composable
fun AirConditionerPage() {
    val coroutineScope = rememberCoroutineScope()

    val airConditionerStatus = LocalAirConditionerAmbient.current

    val environmentData = LocalEnvironmentData.current

    val value = getTemperature(environmentData?.temperature)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundGrey),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IndicatorGraph(
            indicatorValue = value,
            maxIndicatorValue = 50,
            indicatorText = {
                Text(
                    text = "$value°C",
                    color = TextBlack,
                    fontSize = 64.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(y = (-8).dp),
                )
            }
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SensorValue(
                value = "${environmentData?.humidity?.toInt() ?: 0} %",
                description = "Humidity",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            StateSwitcher(
                text = stringResource(R.string.toggle_on_off),
                checked = airConditionerStatus.value.isOn,
                onCheckedChange = { isChecked ->
                    coroutineScope.launch() {
                        try {
                            val result = updateAirConditionerStatus(
                                isChecked,
                                airConditionerStatus.value.speed
                            )
                            if (result) airConditionerStatus.value =
                                AirConditionerStatus(isChecked, airConditionerStatus.value.speed)
                        } catch (e: Exception) {
                        }
                    }
                },
                modifier = Modifier.width(150.dp)
            )
        }
    }
}

fun getTemperature(temperature: Float?): Int {
    val tempInt = temperature?.toInt() ?: 0
    return when {
        tempInt < 0 -> 0
        tempInt > 50 -> 50
        else -> tempInt
    }
}
fun getEnergyUsageText(fanSpeed: Int): String {
    return when (fanSpeed) {
        1 -> "0.8 kWh  ($0.10/h)"
        2 -> "1.2 kWh  ($0.14/h)"
        3 -> "1.5 kWh  ($0.18/h)"
        else -> "0.8 kWh  ($0.10/h)" // Default value (can be adjusted as needed)
    }
}