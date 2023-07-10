package ca.quantum.quants.it.housefy.components.home

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WbIncandescent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DevicesList() {
    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            DeviceCard(
                icon = Icons.Rounded.AcUnit,
                text = "Air Conditioner",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            DeviceCard(
                icon = Icons.Rounded.Air,
                text = "Air Quality",
                modifier = Modifier.weight(1f) // Changed from 0.3f to 1f to match width with items in first row
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DeviceCard(
                icon = Icons.Rounded.EmojiObjects,
                text = "Smart Light",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(1.2f))
        }
    }
}

@Composable
fun DeviceCard(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    val switchState = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
            .fillMaxWidth()
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
                tint = Color(0xFF7468E4)
            )
            Switch(
                checked = switchState.value,
                colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF7468E4)),
                onCheckedChange = { switchState.value = it },
                modifier = Modifier
                    .semantics { contentDescription = "Demo" }
            )
        }

        Spacer(modifier = Modifier.height(56.dp))

        Row() {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.Medium),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row() {
            Text(
                text = "1 device",
                style = MaterialTheme.typography.bodySmall
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = Color(0xFFA3A3A5)),
            )
        }
    }
}