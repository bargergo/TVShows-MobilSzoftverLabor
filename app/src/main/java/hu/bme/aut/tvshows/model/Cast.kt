package hu.bme.aut.tvshows.model

data class Cast (

    val person: Person,
    val character: Character,
    val self: Boolean,
    val voice: Boolean
) {
}