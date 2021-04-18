package hu.bme.aut.tvshows.model

data class ShowSearchResult (

    val score: Float,
    val show: ShowSummary
) {
}