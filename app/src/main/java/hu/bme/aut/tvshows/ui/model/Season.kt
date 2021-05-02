package hu.bme.aut.tvshows.ui.model

data class Season (
    var id: Long?,
    var number: Int,
    var showId: Long,
    var numberOfEpisodes: Int
)