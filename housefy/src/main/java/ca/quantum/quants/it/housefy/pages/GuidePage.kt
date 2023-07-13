package ca.quantum.quants.it.housefy.pages

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuidePage() {
    MaterialTheme {
        Scaffold(
            //topBar = {
            //    TopAppBar(title = { Text("Guide") })
            //}
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(top=30.dp, start = 16.dp, end= 10.dp),
                // .padding(16.dp),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(text = "Understanding the Air Quality Index (AQI)\n",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "The Air Quality Index, commonly referred to as AQI, is a standard system designed to help you understand the quality of air you breathe. It works by taking real-time data on several key pollutants, such as particulate matter (PM2.5, PM10), nitrogen dioxide, sulfur dioxide, carbon monoxide, and ozone, then converting this data into a simplified score and color code.\n")
                }

                item {
                    Text(text = "Understanding Energy Consumption\n",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "When we speak of energy consumption, it's typically measured in kilowatt-hours (kWh). To put it in perspective, a 1,000-watt appliance uses 1 kWh of electricity for one hour of operation. The average energy consumption varies significantly based on location, season, and lifestyle, but we can share some broad estimates based on U.S. and Canadian statistics.\n" +
                            "\n" +
                            "Daily: On average, a U.S. residential utility customer consumes about 30 kWh per day. In Canada, the average daily consumption is about 27 kWh.\n" +
                            "\nWeekly: This scales up to an average of 210 kWh per week in the U.S. and 189 kWh per week in Canada.\n" +
                            "\nMonthly: On a monthly basis, U.S. homes consume around 900 kWh, while in Canada, the average is slightly lower at around 810 kWh.\n" +
                            "\nYearly: Over a year, the average U.S. home consumes about 10,800 kWh. Canadian homes, on the other hand, consume around 9,720 kWh on average.\n" +
                            "\nPlease note that these figures can vary widely based on factors such as the size of the home, the number of occupants, the efficiency of appliances, and more.\n" +
                            "\n" +
                            "The information and figures provided in this guide are given with the only purpose of helping you understand and interpret the information presented in our app, and can vary significantly depending on a number of factors.\n")
                }

                /*
                item {
                    Text(text = "",
                        fontSize = 16.sp)
                }
                */
            }
        }
    }
}
