package ca.quantum.quants.it.housefy.components.settings

import android.app.NotificationManager
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import ca.quantum.quants.it.housefy.R
import kotlin.random.Random

@Composable
fun SettingsRow(
    title: String,
    control: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
                .copy(fontWeight = FontWeight.Medium)
                .copy(color = Color(0xFF353336)),
        )

        control()
    }
}

@Composable
fun sendNotification(context: Context) {
    val notification = NotificationCompat.Builder(context, "water_notification")
        .setSmallIcon(R.drawable.housefy_logo)
        .setContentTitle("Notifications Enabled")
        .setContentText("Notifications for your application were enabled!")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.notify(Random.nextInt(), notification)
}