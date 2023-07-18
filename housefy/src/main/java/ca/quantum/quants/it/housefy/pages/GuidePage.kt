package ca.quantum.quants.it.housefy.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuidePage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.understanding_the_air_quality_index_aqi) + "\n",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = stringResource(R.string.air_quality_guide) + "\n")
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            Text(
                text = stringResource(R.string.understanding_energy_consumption) + "\n",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.energy_guide_line_one) + "\n" +
                        "\n" +
                        stringResource(R.string.energy_guide_line_two) + "\n" +
                        "\n" + stringResource(R.string.energy_guide_line_three) + "\n" +
                        "\n" + stringResource(R.string.energy_guide_line_four) + "\n" +
                        "\n" + stringResource(R.string.energy_guide_line_five) + "\n" +
                        "\n" + stringResource(R.string.energy_guide_line_six) + "\n" +
                        "\n" + stringResource(R.string.energy_guide_line_seven) + "\n"
            )
        }

    }
}
