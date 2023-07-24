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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SignalCellularAlt
import androidx.compose.material.icons.rounded.SignalCellularAlt1Bar
import androidx.compose.material.icons.rounded.SignalCellularAlt2Bar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.AirConditionerAmbient
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.air_continioner.FanSpeedCard
import ca.quantum.quants.it.housefy.components.common.EnergyUsage
import ca.quantum.quants.it.housefy.components.common.IndicatorGraph
import ca.quantum.quants.it.housefy.components.common.StateSwitcher

@Composable
fun AirConditionerPage() {
    val isAirConditionerOn = AirConditionerAmbient.current
    val energyUsageText = remember { mutableStateOf("0.8 kWh  ($0.10/h)") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IndicatorGraph(
            indicatorValue = 25,
            indicatorText = {
                Text(
                    text = "25°C",
                    color = Color(0xFF353336),
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
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            FanSpeedCard(
                icon = Icons.Rounded.SignalCellularAlt1Bar,
                text = stringResource(R.string.low_speed),
                modifier = Modifier.weight(1f),
                onClick = {
                    energyUsageText.value = "0.8 kWh  ($0.10/h)"
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            FanSpeedCard(
                icon = Icons.Rounded.SignalCellularAlt2Bar,
                text = stringResource(R.string.medium_speed),
                modifier = Modifier.weight(1f),
                onClick = {
                    energyUsageText.value = "1.2 kWh  ($0.14/h)"
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            FanSpeedCard(
                icon = Icons.Rounded.SignalCellularAlt,
                text = stringResource(R.string.high_speed),
                modifier = Modifier.weight(1f),
                onClick = {
                    energyUsageText.value = "1.5 kWh  ($0.18/h)"
                }
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            EnergyUsage(
                text = energyUsageText.value,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            StateSwitcher(
                text = stringResource(R.string.toggle_on_off),
                checked = isAirConditionerOn.value,
                onCheckedChange = { isChecked -> isAirConditionerOn.value = isChecked },
                modifier = Modifier.weight(1f)
            )
        }
    }
}