package hu.bme.aut.tvshows.model

data class EpisodeData (

    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Long,
    val image: Image,
    val summary: String
) {
}