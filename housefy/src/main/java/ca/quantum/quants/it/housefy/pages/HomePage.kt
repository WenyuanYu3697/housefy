package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ca.quantum.quants.it.housefy.components.home.DevicesList
import ca.quantum.quants.it.housefy.components.home.WeatherCard
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey

@Composable
fun HomePage(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        HomePageLayout(navController, 16.dp, snackbarHostState)
    } else {
        HomePageLayout(navController, 24.dp, snackbarHostState)
    }
}

@Composable
fun HomePageLayout(navController: NavHostController, spacerSize: Dp, snackbarHostState: SnackbarHostState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundGrey)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        item {
            WeatherCard(snackbarHostState)
        }

        item { Spacer(modifier = Modifier.height(spacerSize)) }

        item { Spacer(modifier = Modifier.height(spacerSize)) }

        item {
            DevicesList(navController)
        }
    }
}