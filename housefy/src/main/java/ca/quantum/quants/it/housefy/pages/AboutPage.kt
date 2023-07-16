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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        item {
            Text(
                text = "About Housefy\n",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Housefy is an innovative smart home Internet of Things (IoT) application that seamlessly integrates technology into every corner of your home, elevating your everyday living experience.\n" +
                        "\n" +
                        "We are dedicated to providing an unparalleled smart home experience that places simplicity, control, and convenience right at your fingertips. With the use of cutting-edge technology, Housefy effortlessly interconnects all your home devices, giving you the power to control and monitor your home, no matter where you are.\n" +
                        "\n" +
                        "Our app is designed with a user-friendly interface that makes it easy to manage a variety of home devices. From security systems and lighting controls to thermostats and home appliances, Housefy enables you to run your home in a smarter, more energy-efficient way. Whether you are at home or on the go, Housefy keeps you connected to what's important the most.\n" +
                        "\n" +
                        "Housefy is more than just an app - it's a lifestyle. It offers the convenience of smart automation, the comfort of energy efficiency and the peace of mind of remote control. It's your home, but smarter. Welcome to Housefy – embrace the future of home automation.\n"
            )
        }
    }
}
