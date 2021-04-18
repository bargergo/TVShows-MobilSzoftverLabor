package hu.bme.aut.tvshows.model

data class Embedded (

    val seasons: List<Season>? = null,
    val cast: List<Cast>? = null
) {
}