package ca.quantum.quants.it.housefy.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.EmojiObjects
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.WbIncandescent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import ca.quantum.quants.it.housefy.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItems(navController: NavHostController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val currentBackStackEntryAsState = navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState.value?.destination

    NavigationDrawerItem(
        icon = { Icon(Icons.Rounded.Home, null) },
        label = { Text(text = stringResource(R.string.home)) },
        selected = destination?.route == "HomePage",
        onClick = {
            navController.navigate("HomePage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch { drawerState.close() }
        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(12.dp))

    NavigationDrawerItem(
        icon = { Icon(Icons.Outlined.AcUnit, null) },
        label = { Text(text = stringResource(R.string.air_conditioner)) },
        selected = destination?.route == "AirConditionerPage",

        onClick = {
            navController.navigate("AirConditionerPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(12.dp))

    NavigationDrawerItem(
        icon = { Icon(Icons.Outlined.Air, null) },
        label = { Text(text = stringResource(R.string.air_quality)) },
        selected = destination?.route == "AirQualityPage",

        onClick = {
            navController.navigate("AirQualityPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(12.dp))

    NavigationDrawerItem(
        icon = { Icon(Icons.Rounded.EmojiObjects, null) },
        label = { Text(text = stringResource(R.string.smart_light)) },
        selected = destination?.route == "SmartLightPage",

        onClick = {
            navController.navigate("SmartLightPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(12.dp))

    NavigationDrawerItem(
        icon = { Icon(Icons.Rounded.Bolt, null) },
        label = { Text(text = stringResource(R.string.energy_consumption)) },
        selected = destination?.route == "EnergyConsumptionPage",

        onClick = {
            navController.navigate("EnergyConsumptionPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}