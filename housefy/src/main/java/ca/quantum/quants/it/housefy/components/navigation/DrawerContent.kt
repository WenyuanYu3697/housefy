package ca.quantum.quants.it.housefy.components.navigation


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