package ca.quantum.quants.it.housefy

import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ca.quantum.quants.it.housefy.pages.getTemperature
import org.junit.Test

@RunWith(RobolectricTestRunner::class)
class AirConditionerPageTest {

    @Test
    fun shouldReturnCorrectTemperatureWhenNonNullDataProvided() {
        val result = getTemperature(25.5f)
        assertEquals(25, result)
    }

    @Test
    fun shouldReturnZeroWhenZeroTemperatureProvided() {
        val result = getTemperature(0f)
        assertEquals(0, result)
    }

    @Test
    fun shouldReturnZeroWhenNullDataProvided() {
        val result = getTemperature(null)
        assertEquals(0, result)
    }

    @Test
    fun shouldReturnZeroWhenNegativeTemperatureProvided() {
        val result = getTemperature(-1f)
        assertEquals(0, result)
    }

    @Test
    fun shouldRoundDownTemperatureWhenFractionalTemperatureProvided() {
        val result = getTemperature(70f)
        assertEquals(50, result)
    }
}
