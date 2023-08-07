package ca.quantum.quants.it.housefy.models

import kotlinx.serialization.Serializable

@Serializable
data class EnvironmentData(
    val temperature: Float,
    val co2: Float,
    val voc: Float,
    val lightLevel: Float,
    val kwh: Float,
)
