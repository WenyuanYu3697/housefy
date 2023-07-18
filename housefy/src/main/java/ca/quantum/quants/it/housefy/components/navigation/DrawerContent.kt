package ca.quantum.quants.it.housefy.components.navigation

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {
    ModalDrawerSheet() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)) {
            DrawerItems(navController, drawerState)
        }
    }
}