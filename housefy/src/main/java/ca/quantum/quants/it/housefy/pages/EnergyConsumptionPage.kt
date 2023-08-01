package ca.quantum.quants.it.housefy.pages

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.stringResource
import ca.quantum.quants.it.housefy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyConsumptionPage() {
    var threshold by remember { mutableStateOf(0.7f) } // Initializing threshold to 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0F2F1)),
        contentAlignment = Alignment.Center
    ) {

        Chart(
            data = listOf(
                Pair(0.9f, 1),
                Pair(0.6f, 2),
                Pair(0.2f, 3),
                Pair(0.7f, 4),
                Pair(0.8f, 5),
                Pair(0.7f, 6),
                Pair(0.7f, 7),
            ), max_value = 50, threshold = threshold
        )
        ThresholdSettings(threshold) { newThreshold ->
            threshold = newThreshold
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThresholdSettings(threshold: Float, onThresholdChange: (Float) -> Unit) {
    Box(
        modifier = Modifier
            .padding(30.dp, 70.dp, 30.dp, 500.dp)
            .absoluteOffset(0.dp, 460.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(5))
            .background(color = Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(
                text = stringResource(R.string.set_threshold),
                color = Color(0xFF7468E4),
                textAlign = TextAlign.Start,
            )
        }

        Column() {
            TextField(
                value = threshold.toString(),
                onValueChange = { onThresholdChange(it.toFloatOrNull() ?: threshold) },
                label = { Text(text = stringResource(R.string.set_threshold)) },
                modifier = Modifier
                    .background(color = Color(0xFFFFFFFF)),
            )
        }
    }
}

@Composable
fun Chart(
    data: List<Pair<Float, Int>>,
    max_value: Int,
    threshold: Float // Adding threshold as a parameter
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(30.dp, 60.dp, 30.dp, 180.dp)
            .clip(RoundedCornerShape(5))
            .background(Color(0xFFFFFFFF))
    ) {
        Column() {
            Text(
                text = "Usage, KWh",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7468E4),
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
                    color = Color(0xFF858585),
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
                        tint = Color(0xFF919191),
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .absoluteOffset(0.dp, 120.dp)
                .padding(20.dp, 5.dp, 20.dp, 225.dp)
                .fillMaxSize()
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
                    Bar(
                        height = it.first,
                        threshold = threshold,
                        maxValue = max_value.toFloat(),
                        onClick = {
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .absoluteOffset(28.dp, 370.dp)
        ) {
            Text(
                text = "Total: 254 KWh",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6D6C6C),
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
            )
        }
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
            Text(text = maxValue.toString(), color = Color(0xFF858585))
            Spacer(modifier = Modifier.fillMaxHeight())
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = (maxValue / 4).toString(),
                color = Color(0xFF858585),
            )
            Spacer(modifier = Modifier.fillMaxHeight(0f))
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = (maxValue / 2).toString(),
                color = Color(0xFF858585),
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
fun Bar(height: Float, threshold: Float, maxValue: Float, onClick: () -> Unit) {
    val context = LocalContext.current

    val totalHeightInDp = 10000.dp
    val heightPercentage = height / maxValue
    val thresholdPercentage = threshold / maxValue

    val heightInDp = heightPercentage * totalHeightInDp.value
    val thresholdInDp = thresholdPercentage * totalHeightInDp.value

    val excessHeightInDp = maxOf(0f, heightInDp - thresholdInDp)
    val belowThresholdHeightInDp = minOf(heightInDp, thresholdInDp)

    val aboveThresholdShape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
    val belowThresholdShape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50)

    Column(
        Modifier
            .padding(start = 15.dp)
            .width(15.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Bottom
    ) {
        if (threshold != 0f) {
            // Part of the bar above the threshold
            Box(
                modifier = Modifier
                    .height(excessHeightInDp.dp)
                    .fillMaxWidth()
                    .clip(aboveThresholdShape)
                    .background(Color.Red)
            )

            Spacer(modifier = Modifier.height(1.dp))
        }

        // Part of the bar below the threshold
        Box(
            modifier = Modifier
                .height((belowThresholdHeightInDp + if (threshold == 0f) excessHeightInDp else 0f).dp)
                .fillMaxWidth()
                .clip(belowThresholdShape)
                .background(Color(0xFF7468E4))
        )
    }
}

@Composable
fun XAxis(data: List<Pair<Float, Int>>) {
    Row(
        modifier = Modifier
            .padding(start = 62.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        data.forEach {
            Text(
                modifier = Modifier.width(20.dp),
                text = it.second.toString(),
                textAlign = TextAlign.Center,
                color = Color(0xFF858585),
            )
        }
    }
}
