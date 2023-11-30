package ca.quantum.quants.it.housefy.components.home

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.EmojiObjects
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import ca.quantum.quants.it.housefy.LocalAirConditionerAmbient
import ca.quantum.quants.it.housefy.LocalAirQualityAmbient
import ca.quantum.quants.it.housefy.LocalLightOnAmbient
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.network.AirConditionerStatus
import ca.quantum.quants.it.housefy.network.updateAirConditionerStatus
import ca.quantum.quants.it.housefy.network.updateAirQualityStatus
import ca.quantum.quants.it.housefy.network.updateLightStatus
import ca.quantum.quants.it.housefy.ui.theme.Purple
import ca.quantum.quants.it.housefy.ui.theme.TextBlack
import ca.quantum.quants.it.housefy.ui.theme.TextGrey
import kotlinx.coroutines.launch

@Composable
fun DevicesList(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    val isLightOn = LocalLightOnAmbient.current
    val airConditionerStatus = LocalAirConditionerAmbient.current
    val isAirQualityOn = LocalAirQualityAmbient.current

    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            DeviceCard(
                icon = Icons.Rounded.AcUnit,
                text = stringResource(R.string.air_conditioner),
                modifier = Modifier.weight(1f),
                checked = airConditionerStatus.value.isOn,
                onCheckedChange = { isChecked ->
                    coroutineScope.launch() {
                        try {
                            val result = updateAirConditionerStatus(
                                isChecked,
                                airConditionerStatus.value.speed
                            )
                            if (result) airConditionerStatus.value = AirConditionerStatus(isChecked, airConditionerStatus.value.speed)
                        } catch (e: Exception) {
                        }
                    }
                },
                onClick = {
                    navController.navigate("AirConditionerPage", navOptions {
                        launchSingleTop = true
                        restoreState = true
                    })
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            DeviceCard(
                icon = Icons.Rounded.Air,
                text = stringResource(R.string.air_quality),
                modifier = Modifier.weight(1f),
                checked = isAirQualityOn.value,
                onCheckedChange = { isChecked ->
                    coroutineScope.launch() {
                        try {
                            val result = updateAirQualityStatus(isChecked)
                            if (result) isAirQualityOn.value = isChecked
                        } catch (e: Exception) {
                        }
                    }
                },
                onClick = {
                    navController.navigate("AirQualityPage", navOptions {
                        launchSingleTop = true
                        restoreState = true
                    })
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DeviceCard(
                icon = Icons.Rounded.EmojiObjects,
                text = stringResource(R.string.smart_light),
                modifier = Modifier.weight(1f),
                checked = isLightOn.value,
                onCheckedChange = { isChecked ->
                    coroutineScope.launch() {
                        try {
                            val result = updateLightStatus(isChecked)
                            if (result) isLightOn.value = isChecked
                        } catch (e: Exception) {
                        }
                    }
                },
                onClick = {
                    navController.navigate("SmartLightPage", navOptions {
                        launchSingleTop = true
                        restoreState = true
                    })
                }
            )
            Spacer(modifier = Modifier.weight(1.2f))
        }
    }
}

@Composable
fun DeviceCard(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = Purple
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(checkedTrackColor = Purple),
            )
        }

        Spacer(modifier = Modifier.height(56.dp))

        Row() {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = TextBlack),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row() {
            Text(
                text = stringResource(R.string.one_device),
                style = MaterialTheme.typography.bodySmall
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = TextGrey),
            )
        }
    }
}