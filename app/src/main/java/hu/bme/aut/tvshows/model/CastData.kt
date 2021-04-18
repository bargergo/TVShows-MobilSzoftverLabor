package hu.bme.aut.tvshows.model

data class CastData (

    val personId: Int,
    val character: CharacterData,
    val self: Boolean,
    val voice: Boolean
) {
}