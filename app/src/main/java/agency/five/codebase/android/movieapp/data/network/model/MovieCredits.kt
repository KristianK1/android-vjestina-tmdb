package agency.five.codebase.android.movieapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("crew")
    val crew: List<ApiCrewman>,
    @SerialName("cast")
    val cast: List<ApiActor>,
)

@Serializable
data class ApiCrewman(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String,
)

@Serializable
data class ApiActor(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val character: String,
    @SerialName("profile_path")
    val imagePath: String?,
)
