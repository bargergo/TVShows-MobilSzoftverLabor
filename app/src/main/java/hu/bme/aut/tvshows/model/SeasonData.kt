package hu.bme.aut.tvshows.model

data class SeasonData (

    val number: Int,
    val name: String,
    val episodeOrder: Int,
    val premiereDate: String,
    val endDate: String,
    val network: Network,
    val webChannel: Any? = null,
    val image: Image,
    val summary: String
) {
}