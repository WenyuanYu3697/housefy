package ca.quantum.quants.it.housefy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.ui.theme.HousefyTheme

val LightOnAmbient = compositionLocalOf<MutableState<Boolean>> { error("No light state provided") }
val AirConditionerAmbient =
    compositionLocalOf<MutableState<Boolean>> { error("No AirConditioner state provided") }

class MainActivity : ComponentActivity() {
    private val isBackDialogShown = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Notification Channel"
            val descriptionText = "Description of the notification channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel("housefy_notification_channel", name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        setContent {
            val isLightOn = remember { mutableStateOf(false) }
            val isAirConditionerOn = remember { mutableStateOf(false) }

            HousefyTheme() {
                CompositionLocalProvider(
                    LightOnAmbient provides isLightOn,
                    AirConditionerAmbient provides isAirConditionerOn,
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Navigation()

                        if (isBackDialogShown.value) {
                            AlertDialog(
                                onDismissRequest = { isBackDialogShown.value = false },
                                title = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            Icons.Outlined.Warning,
                                            contentDescription = "Warning icon"
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Exit App")
                                    }
                                },
                                text = { Text("Do you want to exit the app?") },
                                confirmButton = {
                                    Button(
                                        onClick = { finish() }
                                    ) {
                                        Text("Yes")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { isBackDialogShown.value = false }
                                    ) {
                                        Text("No")
                                    }
                                }
                            )
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