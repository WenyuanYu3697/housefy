package ca.quantum.quants.it.housefy.models

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("FullName")
    val name: String,
    val email: String,
    @SerialName("PhoneModel")
    val deviceName: String,
    @SerialName("PhoneNumber")
    val phoneNumber: String
)

@Serializable
data class Feedback(
    @SerialName("Rate")
    val rating: Int,
    val comment: String,
    val user: User
)

