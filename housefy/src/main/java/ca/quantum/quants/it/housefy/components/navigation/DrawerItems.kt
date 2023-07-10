package ca.quantum.quants.it.housefy.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.EmojiObjects
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItems(navController: NavHostController, drawerState: DrawerState) {
    var scope = rememberCoroutineScope()
    var currentBackStackEntryAsState = navController.currentBackStackEntryAsState()
    var destination = currentBackStackEntryAsState.value?.destination

    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        label = { Text(text = "Home") },
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
        icon = { Icon(Icons.Filled.Thermostat, "Air Conditioner") },
        label = { Text(text = "Air Conditioner") },
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
        icon = { Icon(Icons.Filled.Air, "Air Quality") },
        label = { Text(text = "Air Quality") },
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
        icon = { Icon(Icons.Filled.EmojiObjects, "Smart Light") },
        label = { Text(text = "Smart Light") },
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
        icon = { Icon(Icons.Filled.Bolt, "Energy Consumption") },
        label = { Text(text = "Energy Consumption") },
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