package ca.quantum.quants.it.housefy

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import org.junit.Assert.assertEquals
import org.junit.Test
import androidx.compose.ui.graphics.Color
import ca.quantum.quants.it.housefy.pages.getAQIColor

class AirQualityPageTest {

    @Test
    fun testAQIColorForRange0to25() {
        val result = getAQIColor(10)
        assertEquals(Color(0xFF8CD456), result)
    }

    @Test
    fun testAQIColorForRange26to50() {
        val result = getAQIColor(30)
        assertEquals(Color(0xFFFFE24C), result)
    }

    @Test
    fun testAQIColorForRange51to75() {
        val result = getAQIColor(60)
        assertEquals(Color(0xFFFF0000), result)
    }

    @Test
    fun testAQIColorForAbove75() {
        val result = getAQIColor(80)
        assertEquals(Color(0xFFFFA500), result)
    }

    @Test
    fun testAQIColorForBoundaryValue() {
        val result = getAQIColor(25)
        assertEquals(Color(0xFF8CD456), result)
    }
}
