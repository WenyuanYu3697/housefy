package ca.quantum.quants.it.housefy.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled
import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.quantum.quants.it.housefy.ui.theme.HousefyTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.zIndex

@Composable
fun EnergyConsumptionPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //Text(text = "Energy Consumption Page Content")
        Chart(
            data = listOf(
                Pair(0.9f, 1),
                Pair(0.6f, 2),
                Pair(0.2f, 3),
                Pair(0.7f, 4),
                Pair(0.8f, 5),
                Pair(0.7f, 6),
                Pair(0.7f, 7),
            ), max_value = 50)
    }
}

@Composable
fun Chart(
    data: List<Pair<Float, Int>>,
    max_value: Int
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(30.dp, 60.dp, 30.dp, 180.dp)
            .clip(RoundedCornerShape(5))
            .background(Color(0xFF333340))
        //.background(Color.Red)
    ) {
        Column() {
            Text(

                text = "Usage, KWh",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF707DB2),
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 20.dp)
            )
        }
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp, 68.dp)
            ) {
                Text(
                    text = "July 2023 - Week 1",
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFFFFFFF),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.weight(1f))  // This will take up remaining space
                IconButton(
                    onClick = {
                        // Toast as a placeholder, will be replaced with logic for pop up menu
                        Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
                    }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Options",
                        tint = Color.White,
                        //modifier = Modifier
                            //.align(Alignment.Top)

                    )
                }
            }

        }
//GRAPH BOX IMPLEMENTED WITH COLUMN
        Column(
            modifier = Modifier
                .absoluteOffset(0.dp, 120.dp)
                .padding(20.dp, 5.dp, 20.dp, 225.dp)
                .fillMaxSize()
            //.background(Color.Magenta)
        ) {
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {

                // Y-axis
                YAxis(max_value)

                // Graph columns
                data.forEach {
                    Bar(height = it.first, onClick = {
                        Toast.makeText(context, it.first.toString(), Toast.LENGTH_SHORT).show()
                    })
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .height(2.dp)
                        .background(Color(0xFFFFFFFF))
                )
            }

            XAxis(data)
        }
// GRAPH BOX-COLUMN END

//TOTAL FOR TIME PERIOD START
        Box(
            //verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .absoluteOffset(28.dp, 370.dp)
            //.padding(56.dp, 300.dp)
        ){
            Text(
                text = "Total: 254 KWh",
                fontWeight = FontWeight.Bold,
                //color = Color(0xFFFFFFFF),
                color = Color(0xFF858AE7),
                //color = Color.Black,
                fontSize = 27.sp,
                textAlign = TextAlign.Start,

                )
        }
// TOTAL FOR TIME PERIOD END

    }

}

@Composable
fun YAxis(maxValue: Int) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = maxValue.toString(), color = Color(0xFFFFFFFF),)
            Spacer(modifier = Modifier.fillMaxHeight())
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = (maxValue / 4).toString(),
                color = Color(0xFFFFFFFF),
            )
            Spacer(modifier = Modifier.fillMaxHeight(0f))
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = (maxValue / 2).toString(),
                color = Color(0xFFFFFFFF),
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
        }
    }

    Box(
        modifier = Modifier
            .width(2.dp)
            .background(Color(0xFFFFFFFF)),
    )
}

@Composable
fun Bar(height: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            //makes columns closer to each other
            .padding(start = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .width(15.dp)
            .fillMaxHeight(height)
            .background(Color(0xFF187BD3))
            .clickable { onClick() }
    )
}

@Composable
fun XAxis(data: List<Pair<Float, Int>>) {
    Row(
        modifier = Modifier
            //remoteness of 1st column from y-axis
            .padding(start = 62.dp)
            .fillMaxWidth(),
        //distance between x axis values
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        data.forEach {
            Text(
                modifier = Modifier.width(20.dp),
                text = it.second.toString(),
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF),
            )
        }
    }
}

