package ca.quantum.quants.it.housefy.components.smart_light

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun EnergyUsage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "0.01kWh  ($0.13/h)",
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = Color(0xFF353336))
                    .copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
            )
            Text(
                text = "Energy usage",
                style = MaterialTheme.typography.bodySmall
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = Color(0xFFA3A3A5)),
            )
        }
    }

}
