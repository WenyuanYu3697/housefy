package ca.quantum.quants.it.housefy.components.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.R

@Composable
fun LogoAndDescription() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFF0F2F1))) {
        Image(
            painter = painterResource(id = R.drawable.housefy_logo),
            contentDescription = "App Logo",
            modifier = Modifier.align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp, start = 48.dp, end = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Housefy", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.SemiBold)
                    .copy(color = Color(0xFF353336)),
            )
            Text(
                text = "Smart Home Environment and Energy Monitoring System",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
                    .copy(fontWeight = FontWeight.Normal)
                    .copy(color = Color(0xFF353336)),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
