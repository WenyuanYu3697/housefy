package ca.quantum.quants.it.housefy

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.quantum.quants.it.housefy.pages.AirConditionerPage
import ca.quantum.quants.it.housefy.pages.AirQualityPage
import ca.quantum.quants.it.housefy.pages.HomePage
import ca.quantum.quants.it.housefy.pages.SmartLightPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomePageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val airConditionerState = remember { mutableStateOf(false) }
            val lightState = remember { mutableStateOf(false) }

            CompositionLocalProvider(
                AirConditionerAmbient provides airConditionerState,
                LightOnAmbient provides lightState
            ) {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                NavHost(navController = navController, startDestination = "HomePage") {
                    composable("HomePage") { HomePage(navController, snackbarHostState) }
                    composable("AirConditionerPage") { AirConditionerPage() }
                    composable("AirQualityPage") { AirQualityPage() }
                    composable("SmartLightPage") { SmartLightPage() }
                }
            }
        }
    }

    @Test
    fun testNavigationToAirConditionerPage() {
        composeTestRule.onNodeWithText("Air Conditioner").performClick()
        composeTestRule.onNodeWithText("Low speed").assertExists()
    }

    @Test
    fun testNavigationToAirQualityPage() {
        composeTestRule.onNodeWithText("Air Quality").performClick()
        composeTestRule.onNodeWithText("Smart Light").assertExists()
    }

    @Test
    fun testNavigationToSmartLightPage() {
        composeTestRule.onNodeWithText("Smart Light").performClick()
        composeTestRule.onNodeWithText("Toggle on/off").assertExists()
    }

    @Test
    fun testEnergyUsageTextExists() {
        val textNode = composeTestRule.onNodeWithText("Energy Usage")
        textNode.assertExists()
    }

    @Test
    fun testEnergyConsumptionTextExists() {
        val textNode = composeTestRule.onNodeWithText("Energy Consumption")
        textNode.assertExists()
    }

}
