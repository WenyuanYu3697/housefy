package ca.quantum.quants.it.housefy.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.settings.SettingsRow

@SuppressLint("MissingPermission")
@Composable
fun SettingsPage() {
    val context = LocalContext.current
    val activity = context as? Activity

    val lockPortrait = remember { mutableStateOf(false) }
    val enableNotifications = remember { mutableStateOf(false) }
    var airQualityThreshold by remember { mutableStateOf(0f) }
    var airTemperatureThreshold by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {

        val portraitLocked = stringResource(R.string.portrait_mode_locked)
        val portraitUnlocked = stringResource(R.string.portrait_mode_unlocked)

        Column() {
            SettingsRow(
                title = stringResource(R.string.lock_portrait),
                control = {
                    Switch(
                        checked = lockPortrait.value,
                        colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF7468E4)),
                        onCheckedChange = { newValue ->
                            lockPortrait.value = newValue

                            val toastMessage =
                                if (newValue) portraitLocked
                                else portraitUnlocked
                            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

                            activity?.requestedOrientation = if (newValue) {
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            } else {
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            }
                        }
                    )
                }
            )

            val notificationChannel = stringResource(R.string.housefy_notification_channel)
            val notificationTitle = stringResource(R.string.app_name)
            val notificationText = stringResource(R.string.notifications_enabled)
            SettingsRow(
                title = stringResource(R.string.enable_notifications),
                control = {
                    Switch(
                        checked = enableNotifications.value,
                        colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF7468E4)),
                        onCheckedChange = { newValue ->
                            enableNotifications.value = newValue

                            if (newValue) {
                                val builder = NotificationCompat.Builder(
                                    context, notificationChannel
                                )
                                    .setSmallIcon(R.drawable.housefy_logo)
                                    .setContentTitle(notificationTitle)
                                    .setContentText(notificationText)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setAutoCancel(true)

                                with(NotificationManagerCompat.from(context)) {
                                    notify(1, builder.build())
                                }
                            }
                        }
                    )
                }
            )

            SettingsRow(
                title = stringResource(R.string.air_quality_threshold),
                control = {
                    Slider(
                        value = airQualityThreshold,
                        onValueChange = { airQualityThreshold = it },
                        steps = 5,
                        valueRange = 20f..100f,
                        modifier = Modifier.width(150.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF7468E4),
                        )
                    )
                }
            )

            SettingsRow(
                title = stringResource(R.string.temperature_threshold),
                control = {
                    Slider(
                        value = airTemperatureThreshold,
                        onValueChange = { airTemperatureThreshold = it },
                        steps = 5,
                        valueRange = 0f..50f,
                        modifier = Modifier.width(150.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF7468E4),
                        )
                    )
                }
            )
        }
    }
}
