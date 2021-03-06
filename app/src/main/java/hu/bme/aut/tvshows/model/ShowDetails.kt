package hu.bme.aut.tvshows.model

import org.threeten.bp.LocalDate

data class ShowDetails (

    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Long,
    val premiered: LocalDate? = null,
    val officialSite: String,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network,
    val webChannel: Any? = null,
    val dvdCountry: Any? = null,
    val externals: Externals,
    val image: Image? = null,
    val summary: String? = null,
    val updated: Long,
    val links: Links,
    val embedded: Embedded? = null
) {
}