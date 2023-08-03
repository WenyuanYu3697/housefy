package ca.quantum.quants.it.housefy.components.home

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.asynctasks.getWeatherData
import ca.quantum.quants.it.housefy.ui.theme.Purple
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@SuppressLint("ServiceCast", "MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherCard(snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val temperature = remember { mutableStateOf("N/A") }
    val feelsLike = remember { mutableStateOf("N/A") }
    val icon = remember { mutableStateOf("01d") }

    val fineLocationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION) { isGranted ->
            if (!isGranted) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "Permission denied")
                }
            }
        }

    if (fineLocationPermissionState.status.isGranted) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        val locationGPS = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        if (locationGPS != null) {
            val lat = locationGPS.latitude.toString()
            val lon = locationGPS.longitude.toString()
            getWeatherData(
                lat,
                lon,
                "e5d0aeca41a181bf5eda281dd41ff4af",
                temperature,
                feelsLike,
                icon
            )
        } else {
            getWeatherData(
                "43.728275",
                "-79.606124",
                "e5d0aeca41a181bf5eda281dd41ff4af",
                temperature,
                feelsLike,
                icon
            )
        }

        PermissionGranted(
            temp = temperature.value,
            feelsLike = feelsLike.value,
            icon = icon.value
        )
    } else {
        PermissionNotGranted(onClick = {
            fineLocationPermissionState.launchPermissionRequest()
        })
    }
}

@Composable
fun PermissionGranted(temp: String, feelsLike: String, icon: String) {
    Box(
        modifier = Modifier
            .background(
                color = Purple,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Box(modifier = Modifier.align(Alignment.TopStart)) {
            Image(
                painter = painterResource(id = mapWeatherIconToAppIcon(icon)),
                contentDescription = null,
                modifier = Modifier
                    .width(64.dp)
                    .absoluteOffset(
                        y = if (mapWeatherIconToAppIcon(icon) == R.drawable.weather_rainy) {
                            (-20).dp
                        } else {
                            (-30).dp
                        },
                        x = if (mapWeatherIconToAppIcon(icon) == R.drawable.weather_rainy) {
                            (-10).dp
                        } else {
                            (-4).dp
                        },
                    )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "$temp°",
                style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                fontSize = 32.sp
            )
            Text(
                text = stringResource(R.string.feels_like) + " " + "$feelsLike°",
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        }
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            Image(
                painter = painterResource(id = mapWeatherIconToAppIcon(icon)),
                contentDescription = null,
                modifier = Modifier
                    .width(64.dp)
                    .absoluteOffset(y = 24.dp, x = 12.dp)
            )
        }
    }
}

@Composable
fun PermissionNotGranted(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = Purple,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.grant_access_to_location_in_order_to_see_weather),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun mapWeatherIconToAppIcon(icon: String): Int {
    return when (icon) {
        "01d", "01n", "02d", "02n" -> R.drawable.weather_sunny
        "03d", "03n", "04d", "04n" -> R.drawable.weather_cloudy
        "11d", "11n" -> R.drawable.weather_thunder
        else -> R.drawable.weather_rainy
    }
}