package hu.bme.aut.tvshows.ui.model

data class Episode(
    var id: Long,
    var name: String,
    var number: Int,
    var summary: String,
    var season: Int
)
