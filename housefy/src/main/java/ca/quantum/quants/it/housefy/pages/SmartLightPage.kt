package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.LightOnAmbient
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.common.EnergyUsage
import ca.quantum.quants.it.housefy.components.common.StateSwitcher

@Composable
fun SmartLightPage() {
    val isLightOn = LightOnAmbient.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (isLightOn.value) R.drawable.smart_light_on else R.drawable.smart_light_off),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .scale(if (isLightOn.value) 2.1f else 1.7f)
                .align(Alignment.CenterHorizontally)
                .padding(top = 75.dp)
                .offset(
                    y = if (isLightOn.value) 0.dp else 26.dp,
                    x = if (isLightOn.value) 0.dp else 2.dp
                )
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            EnergyUsage(
                text = "0.01kWh  ($0.13/h)",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            StateSwitcher(
                text = stringResource(R.string.toggle_on_off),
                checked = isLightOn.value,
                onCheckedChange = { isChecked -> isLightOn.value = isChecked },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
