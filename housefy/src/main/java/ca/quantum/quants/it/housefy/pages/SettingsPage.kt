package ca.quantum.quants.it.housefy.pages

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.components.settings.SettingsRow

@Composable
fun SettingsPage() {
    val context = LocalContext.current
    val activity = context as? Activity

    val lockPortrait = remember { mutableStateOf(false) }
    val enableNotifications = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {

        Column() {
            SettingsRow(
                title = "Lock Portrait",
                control = {
                    Switch(
                        checked = lockPortrait.value,
                        onCheckedChange = { newValue ->
                            lockPortrait.value = newValue
                            activity?.requestedOrientation = if (newValue) {
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            } else {
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            }
                        }
                    )
                }
            )

            SettingsRow(
                title = "Enable Notifications",
                control = {
                    Switch(
                        checked = enableNotifications.value,
                        onCheckedChange = { enableNotifications.value = it }
                    )
                }
            )
        }
    }
}
