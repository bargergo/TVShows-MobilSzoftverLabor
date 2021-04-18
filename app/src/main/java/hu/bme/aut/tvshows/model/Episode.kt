package hu.bme.aut.tvshows.model

data class Episode (

    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Long,
    val image: Image? = null,
    val summary: String,
    val links: Links
) {
}