package ca.quantum.quants.it.housefy.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.smart_light.EnergyUsage
import ca.quantum.quants.it.housefy.components.smart_light.StateSwitcher

@Composable
fun SmartLightPage() {
    val switchState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (switchState.value) R.drawable.smart_light_on else R.drawable.smart_light_off),
            contentDescription = "Center Image",
            modifier = Modifier
                .size(400.dp)
                .scale(if (switchState.value) 1.5f else 1.23f)
                .align(Alignment.CenterHorizontally)
                .padding(top = 75.dp)
                .offset(
                    y = if (switchState.value) 0.dp else 25.dp,
                    x = if (switchState.value) 0.dp else 2.dp
                )
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 150.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            EnergyUsage(
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            StateSwitcher(
                switchState,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}
