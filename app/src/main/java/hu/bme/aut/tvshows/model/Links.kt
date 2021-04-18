package hu.bme.aut.tvshows.model

data class Links (

    val self: LinksSelf,
    val previousepisode: LinksSelf? = null
) {
}