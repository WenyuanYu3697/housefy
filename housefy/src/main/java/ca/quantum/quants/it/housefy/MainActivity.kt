package ca.quantum.quants.it.housefy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.SnackbarHostState
import ca.quantum.quants.it.housefy.ui.theme.HousefyTheme

val LightOnAmbient = compositionLocalOf<MutableState<Boolean>> { error("No light state provided") }
val AirConditionerAmbient =
    compositionLocalOf<MutableState<Boolean>> { error("No AirConditioner state provided") }

class MainActivity : ComponentActivity() {
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
                    }
                }
            }
        }
    }
}