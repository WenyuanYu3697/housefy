package ca.quantum.quants.it.housefy.components.home

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ca.quantum.quants.it.housefy.EnvironmentDataListLocal
import ca.quantum.quants.it.housefy.R
import ca.quantum.quants.it.housefy.ui.theme.TextBlack
import ca.quantum.quants.it.housefy.ui.theme.TextGrey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun EnergyUsageCard(navController: NavHostController) {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
    val formattedDate = today.format(formatter)

    val environmentDataList = EnvironmentDataListLocal.current
    val currentData = environmentDataList.getOrNull(1)

    val value = currentData?.kwh ?: 0

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.energy_usage),
                style = MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = TextBlack),
            )
            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.bodySmall
                    .copy(fontWeight = FontWeight.Medium)
                    .copy(color = TextBlack),
                modifier = Modifier.clickable {
                    navController.navigate("EnergyConsumptionPage") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall
                        .copy(fontWeight = FontWeight.Medium)
                        .copy(color = TextBlack),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.today),
                    style = MaterialTheme.typography.bodySmall
                        .copy(fontWeight = FontWeight.SemiBold)
                        .copy(color = TextGrey),
                )
                Text(
                    text = stringResource(R.string.this_month),
                    style = MaterialTheme.typography.bodySmall
                        .copy(fontWeight = FontWeight.SemiBold)
                        .copy(color = TextGrey),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$value kWh",
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Medium)
                        .copy(color = TextBlack),
                )
                Text(
                    text = "$value kWh",
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Medium)
                        .copy(color = TextBlack),
                )
            }
        }
    }
}