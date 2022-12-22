package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
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
) {
    fun toCrewman(): Crewman {
        return Crewman(
            id = id,
            job = job,
            name = name
        )
    }
}

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
) {
    fun toActor(): Actor {
        return Actor(
            id = id,
            name = name,
            character = character,
            imageUrl = imagePath
        )
    }
}
