package data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    @SerialName("name")
    val name: Name? = null,
    @SerialName("flags")
    val flags: Flags? = null,
) {

    @Serializable
    data class Name(
        @SerialName("common")
        val common: String? = null,
    )

    @Serializable
    data class Flags(
        @SerialName("png")
        val png: String? = null,
    )
}