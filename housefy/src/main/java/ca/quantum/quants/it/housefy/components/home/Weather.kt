package ca.quantum.quants.it.housefy.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.R

@Composable
fun TemperatureCard() {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFF7468E4),
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Box(modifier = Modifier.align(Alignment.TopStart)) {
            Image(
                painter = painterResource(id = R.drawable.weather_cloudy),
                contentDescription = null,
                modifier = Modifier.width(64.dp).absoluteOffset(y = (-30).dp, x = (-4).dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "32°",
                style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                fontSize = 32.sp
            )
            Text(
                text = "Feels like 30°",
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        }
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            Image(
                painter = painterResource(id = R.drawable.weather_cloudy),
                contentDescription = null,
                modifier = Modifier.width(64.dp).absoluteOffset(y = 24.dp, x = 12.dp)
            )
        }
    }
}
