package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
import androidx.core.content.edit
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.components.settings.SettingsRow
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.Purple

@SuppressLint("MissingPermission")
@Composable
fun SettingsPage() {
    val context = LocalContext.current
    val activity = context as? Activity

    val preferences = context.getSharedPreferences("housefy_preferences", Context.MODE_PRIVATE)

    // Load values from SharedPreferences or set default values if not found
    var lockPortrait by remember { mutableStateOf(preferences.getBoolean("lockPortrait", false)) }
    var enableNotifications by remember {
        mutableStateOf(
            preferences.getBoolean(
                "enableNotifications",
                false
            )
        )
    }
    var airQualityThreshold by remember {
        mutableStateOf(
            preferences.getFloat(
                "airQualityThreshold",
                0f
            )
        )
    }
    var airTemperatureThreshold by remember {
        mutableStateOf(
            preferences.getFloat(
                "airTemperatureThreshold",
                0f
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundGrey)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {

        val portraitLocked = stringResource(R.string.portrait_mode_locked)
        val portraitUnlocked = stringResource(R.string.portrait_mode_unlocked)

        Column() {
            SettingsRow(
                title = stringResource(R.string.lock_portrait),
                control = {
                    Switch(
                        checked = lockPortrait,
                        colors = SwitchDefaults.colors(checkedTrackColor = Purple),
                        onCheckedChange = { newValue ->
                            lockPortrait = newValue

                            preferences.edit { putBoolean("lockPortrait", newValue) }

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
                        checked = enableNotifications,
                        colors = SwitchDefaults.colors(checkedTrackColor = Purple),
                        onCheckedChange = { newValue ->
                            enableNotifications = newValue

                            preferences.edit { putBoolean("enableNotifications", newValue) }

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
                        onValueChange = { newValue ->
                            airQualityThreshold = newValue

                            preferences.edit {
                                putFloat("airQualityThreshold", newValue)
                            }
                        },
                        steps = 5,
                        valueRange = 20f..100f,
                        modifier = Modifier.width(150.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Purple,
                        )
                    )
                }
            )

            SettingsRow(
                title = stringResource(R.string.temperature_threshold),
                control = {
                    Slider(
                        value = airTemperatureThreshold,
                        onValueChange = { newValue ->
                            airTemperatureThreshold = newValue

                            preferences.edit {
                                putFloat("airTemperatureThreshold", newValue)
                            }
                        },
                        steps = 5,
                        valueRange = 0f..50f,
                        modifier = Modifier.width(150.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Purple,
                        )
                    )
                }
            )
        }
    }
}
