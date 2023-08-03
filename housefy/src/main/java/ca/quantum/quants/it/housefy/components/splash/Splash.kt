package ca.quantum.quants.it.housefy.components.splash

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.TextBlack

@Composable
fun LogoAndDescription() {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = BackgroundGrey)) {

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.housefy_logo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                DescriptionText()
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.housefy_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
            DescriptionText(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp, start = 48.dp, end = 48.dp)
            )
        }
    }
}

@Composable
fun DescriptionText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
                .copy(fontWeight = FontWeight.SemiBold)
                .copy(color = TextBlack),
        )
        Text(
            text = stringResource(R.string.app_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
                .copy(fontWeight = FontWeight.Normal)
                .copy(color = TextBlack),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
