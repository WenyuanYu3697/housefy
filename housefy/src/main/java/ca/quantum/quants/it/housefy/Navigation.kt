package ca.quantum.quants.it.housefy

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ca.quantum.quants.it.housefy.components.navigation.ActionMenuItem
import ca.quantum.quants.it.housefy.components.navigation.ActionsMenu

import ca.quantum.quants.it.housefy.components.navigation.DrawerContent
import ca.quantum.quants.it.housefy.pages.AboutPage
import ca.quantum.quants.it.housefy.pages.AirConditionerPage
import ca.quantum.quants.it.housefy.pages.AirQualityPage
import ca.quantum.quants.it.housefy.pages.EnergyConsumptionPage
import ca.quantum.quants.it.housefy.pages.FeedbackPage
import ca.quantum.quants.it.housefy.pages.GuidePage
//import ca.quantum.quants.it.housefy.pages.FeedbackPage
import ca.quantum.quants.it.housefy.pages.HomePage
import ca.quantum.quants.it.housefy.pages.SettingsPage
import ca.quantum.quants.it.housefy.pages.SmartLightPage
import ca.quantum.quants.it.housefy.pages.ThresholdViewModel
import ca.quantum.quants.it.housefy.ui.theme.BackgroundGrey
import ca.quantum.quants.it.housefy.ui.theme.Purple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController, drawerState) },
        drawerState = drawerState
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                val textColor = MaterialTheme.colorScheme.secondary

                CenterAlignedTopAppBar(
                    title = { TopBarTitle(navController, textColor) },
                    navigationIcon = {
                        TopBarNavigationIcon(
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                    },
                    actions = { TopBarMenu(navController) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BackgroundGrey
                    )
                )
            }) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = "HomePage") {
                    val thresholdViewModel = ThresholdViewModel()
                    composable("HomePage") { HomePage(navController, snackbarHostState) }
                    composable("AirConditionerPage") { AirConditionerPage() }
                    composable("AirQualityPage") { AirQualityPage() }
                    composable("SmartLightPage") { SmartLightPage() }
                    composable("EnergyConsumptionPage") { EnergyConsumptionPage(thresholdViewModel) }
                    composable("SettingsPage") { SettingsPage(thresholdViewModel) }
                    composable("FeedbackPage") { FeedbackPage() }
                    composable("GuidePage") { GuidePage() }
                    composable("AboutPage") { AboutPage() }
                }

                FloatingActionButton(
                    onClick = {
                        navController.navigate("HomePage", navOptions {
                            this.launchSingleTop = true
                            this.restoreState = true
                        })
                    },
                    containerColor = Purple,
                    contentColor = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Rounded.Home, contentDescription = "Home")
                }
            }
        }
    }
}

@Composable
fun TopBarTitle(navController: NavController, textColor: Color) {
    val title = when (navController.currentBackStackEntryAsState().value?.destination?.route) {
        "AirConditionerPage" -> stringResource(R.string.air_conditioner)
        "AirQualityPage" -> stringResource(R.string.air_quality)
        "SmartLightPage" -> stringResource(R.string.smart_light)
        "EnergyConsumptionPage" -> stringResource(R.string.energy_consumption)
        "SettingsPage" -> stringResource(R.string.settings)
        "FeedbackPage" -> stringResource(R.string.feedback)
        "GuidePage" -> stringResource(R.string.guide)
        "AboutPage" -> stringResource(R.string.about)
        else -> stringResource(R.string.home)
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
fun TopBarMenu(navController: NavController) {
    var isOpen by remember { mutableStateOf(false) }

    val menuItems = listOf<ActionMenuItem>(
        ActionMenuItem.IconMenuItem.AlwaysShown(
            title = stringResource(R.string.settings),
            contentDescription = null,
            onClick = { navController.navigate("SettingsPage") },
            icon = Icons.Rounded.Settings,
        ),
        ActionMenuItem.IconMenuItem.ShownIfRoom(
            title = stringResource(R.string.guide),
            contentDescription = null,
            onClick = {
                isOpen = false;
                navController.navigate("GuidePage")
            },
            icon = Icons.Filled.QuestionAnswer,
        ),
        ActionMenuItem.NeverShown(
            title = stringResource(R.string.feedback),
            onClick = {
                isOpen = false;
                navController.navigate("FeedbackPage")
            },
        ),
        ActionMenuItem.NeverShown(
            title = stringResource(R.string.about),
            onClick = {
                isOpen = false;
                navController.navigate("AboutPage")
            },
        )
    )

    ActionsMenu(
        items = menuItems,
        isOpen = isOpen,
        onToggleOverflow = { isOpen = !isOpen },
        maxVisibleItems = 1
    )
}