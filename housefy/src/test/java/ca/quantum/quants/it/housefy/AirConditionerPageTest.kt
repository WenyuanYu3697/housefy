package ca.quantum.quants.it.housefy

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import org.junit.Assert.assertEquals
import org.junit.Test
import ca.quantum.quants.it.housefy.pages.getTemperature

class AirConditionerPageTest {

    @Test
    fun `test getTemperature with non-null data`() {
        val result = getTemperature(25.5f)
        assertEquals(25, result)
    }

    @Test
    fun `test getTemperature with null temperature`() {
        val result = getTemperature(0f)
        assertEquals(0, result)
    }

    @Test
    fun `test getTemperature with null data`() {
        val result = getTemperature(null)
        assertEquals(0, result)
    }

    @Test
    fun `test getTemperature with fractional temperature`() {
        val result = getTemperature(27.9f)
        assertEquals(27, result)
    }

    @Test
    fun `test getTemperature failing test`() {
        val result = getTemperature(30.5f)
        assertEquals(31, result)
    }
}
