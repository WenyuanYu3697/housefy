package ca.quantum.quants.it.housefy

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.models.EnvironmentData
import ca.quantum.quants.it.housefy.network.fetchEnvironmentData
import ca.quantum.quants.it.housefy.ui.theme.HousefyTheme
import kotlinx.coroutines.delay

val LightOnAmbient = compositionLocalOf<MutableState<Boolean>> { error("No light state provided") }
val AirConditionerAmbient =
    compositionLocalOf<MutableState<Boolean>> { error("No AirConditioner state provided") }
val EnvironmentDataListLocal = compositionLocalOf<List<EnvironmentData>> { emptyList() }
val CurrentDataIndexLocal = compositionLocalOf { 0 }

@Composable
fun EnvironmentDataProvider(content: @Composable () -> Unit) {
    var environmentDataList by remember { mutableStateOf(emptyList<EnvironmentData>()) }
    var currentDataIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = "fetchData") {
        try {
            val newData = fetchEnvironmentData()
            if (!newData.isNullOrEmpty()) {
                environmentDataList = newData
            }
        } catch (e: Exception) {
            Log.e("Environment Data", "Error during fetching environment data")
        }
    }

    LaunchedEffect(key1 = environmentDataList) {
        while (environmentDataList.isNotEmpty()) {
            currentDataIndex = (currentDataIndex + 1) % environmentDataList.size
            delay(3000)
        }
    }

    CompositionLocalProvider(
        EnvironmentDataListLocal provides environmentDataList,
        CurrentDataIndexLocal provides currentDataIndex,
    ) {
        content()
    }
}

class MainActivity : ComponentActivity() {
    private val isBackDialogShown = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = getString(R.string.notification_channel)
        val descriptionText = getString(R.string.description_of_the_notification_channel)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel =
            NotificationChannel(getString(R.string.housefy_notification_channel), name, importance)
        mChannel.description = descriptionText
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        setContent {
            val isLightOn = remember { mutableStateOf(false) }
            val isAirConditionerOn = remember { mutableStateOf(false) }

            HousefyTheme() {
                CompositionLocalProvider(
                    LightOnAmbient provides isLightOn,
                    AirConditionerAmbient provides isAirConditionerOn,
                ) {
                    EnvironmentDataProvider {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Navigation()

                            if (isBackDialogShown.value) {
                                AlertDialog(
                                    onDismissRequest = { isBackDialogShown.value = false },
                                    title = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                Icons.Outlined.Warning,
                                                contentDescription = null
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(getString(R.string.exit_app))
                                        }
                                    },
                                    text = { Text(getString(R.string.do_you_want_to_exit_the_app)) },
                                    confirmButton = {
                                        Button(
                                            onClick = { finish() }
                                        ) {
                                            Text(getString(R.string.yes))
                                        }
                                    },
                                    dismissButton = {
                                        Button(
                                            onClick = { isBackDialogShown.value = false }
                                        ) {
                                            Text(getString(R.string.no))
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onBackPressed() {
        isBackDialogShown.value = true
    }
}