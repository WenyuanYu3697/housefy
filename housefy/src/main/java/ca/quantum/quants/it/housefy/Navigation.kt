package ca.quantum.quants.it.housefy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import ca.quantum.quants.it.housefy.components.navigation.DrawerContent
import ca.quantum.quants.it.housefy.pages.AirConditionerPage
import ca.quantum.quants.it.housefy.pages.AirQualityPage
import ca.quantum.quants.it.housefy.pages.EnergyConsumptionPage
import ca.quantum.quants.it.housefy.pages.HomePage
import ca.quantum.quants.it.housefy.pages.SmartLightPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController, drawerState) },
        drawerState = drawerState
    ) {
        Scaffold(topBar = {
            val textColor = MaterialTheme.colorScheme.secondary

            CenterAlignedTopAppBar(
                title = { TopBarTitle(navController, textColor) },
                navigationIcon = {
                    TopBarNavigationIcon(
                        coroutineScope = coroutineScope,
                        drawerState = drawerState
                    )
                },
                actions = { TopBarMenu() },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF0F2F1)
                )
            )
        }) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = "HomePage") {
                    composable("HomePage") { HomePage(navController) }
                    composable("AirConditionerPage") { AirConditionerPage() }
                    composable("AirQualityPage") { AirQualityPage() }
                    composable("SmartLightPage") { SmartLightPage() }
                    composable("EnergyConsumptionPage") { EnergyConsumptionPage() }
                }
            }
        }
    }
}

@Composable
fun TopBarTitle(navController: NavController, textColor: Color) {
    val title = when (navController.currentBackStackEntryAsState().value?.destination?.route) {
        "AirConditionerPage" -> "Air Conditioner"
        "AirQualityPage" -> "Air Quality"
        "SmartLightPage" -> "Smart Light"
        "EnergyConsumptionPage" -> "Energy Consumption"
        else -> "Home"
    }

    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
            .copy(fontWeight = FontWeight.SemiBold)
            .copy(color = textColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigationIcon(coroutineScope: CoroutineScope, drawerState: DrawerState) {
    IconButton(onClick = {
        if (drawerState.isClosed) {
            coroutineScope.launch {
                drawerState.open()
            }
        } else {
            coroutineScope.launch {
                drawerState.close()
            }
        }
    }) {
        Icon(Icons.Filled.Menu, contentDescription = "Drawer Menu.")
    }
}

@Composable
fun TopBarMenu() {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = true }) {
        Icon(Icons.Filled.MoreVert, contentDescription = "More")
    }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(text = { Text("Guide") }, onClick = { /* Handle item click */ })
        DropdownMenuItem(text = { Text("Feedback") }, onClick = { /* Handle item click */ })
        DropdownMenuItem(text = { Text("About") }, onClick = { /* Handle item click */ })
    }
}